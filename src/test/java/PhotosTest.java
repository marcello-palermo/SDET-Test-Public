import api.photos.Integration;
import api.photos.models.Photo;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.Generic;

import java.util.*;
import java.util.stream.Collectors;

public class PhotosTest {

    //Shared variables between tests
    List<Photo> photoList = new ArrayList<>();
    List<Photo> photoSubListSol = new ArrayList<>();
    List<Photo> photoSubListEarthDate = new ArrayList<>();
    String earthDate = "";

    /**
     * BeforeSuite in this case is used for obtaining useful data that will be needed in the following tests, in favor
     * of avoiding performing multiple requests when not strictly necessary, there is also a validation to ensure correct
     * access the API.
     */
    @BeforeSuite
    public void beforeSuite(){
        Response resp = Integration.getData(earthDate);
        Assert.assertEquals(resp.getStatusCode(),200,"There was a problem with the request");
        photoList = new ArrayList<>(Generic.getResponseAsList(resp).getPhotos());
        earthDate = Generic.getResponseAsList(resp).getPhotos().get(0).getEarthDate();
    }

    /**
     * Retrieve the first 10 Mars photos made by "Curiosity" on 1000 Martian sol.
     *
     * This test obtains the first 10 photos from the complete photo collection obtained in the request performed
     * in the BeforeSuite method and then performs an assertion of all elements to validate they contain
     * the corresponding martian sol date.
     */
    @Test
    public void test_1(){
        photoSubListSol = photoList.stream().limit(10).collect(Collectors.toList());
        Assert.assertTrue(photoSubListSol.stream().allMatch(p -> p.getSol() == 1000),
                "There was a mismatch between the martian sol requested and at least one of the provided in an element in the response");
    }

    /**
     * Retrieve the first 10 Mars photos made by "Curiosity" on Earth date equal to 1000 Martian sol.
     *
     * This test obtains all photos from the corresponding vehicle at the given earth date (obtained from the first test,
     * which is set to 1000 Martian Sol, but could be modified if another date is provided), and then filters out the
     * first 10 results, asserting all elements contain the corresponding earth date.
     */
    @Test
    public void test_2(){
        Response resp2 = Integration.getData(earthDate);
        photoSubListEarthDate = Generic.getResponseAsList(resp2).getPhotos().stream().limit(10).collect(Collectors.toList());
        Assert.assertTrue(photoSubListEarthDate.stream().allMatch(p -> p.getEarthDate().equals(earthDate)),
                "There was a mismatch between the earth date requested and at least one of the provided in an element in the response");
    }

    /**
     * Retrieve and compare the first 10 Mars photos made by "Curiosity" on 1000 sol and on Earth date equal to 1000 Martian sol.
     *
     * This test uses the obtained results from the previous tests, flattens them to a json string and compares them for an exact match.
     */
    @Test(dependsOnMethods = { "test_1", "test_2" })
    public void test_3(){
        Assert.assertEquals(new Gson().toJson(photoSubListSol), new Gson().toJson(photoSubListEarthDate),
                "Differences were found between the elements in both collections");
    }

    /**
     * Validate that the amount of pictures that each "Curiosity" camera took on 1000 Mars sol is not greater than 10
     * times the amount taken by other cameras on the same date.
     *
     * This test takes advantage of the photoListComplete object containing all entries in the response for the given query,
     * and extracts all distinct camera names used, then prepares a map and iterates on the collection obtaining the quantity
     * of photos taken on each of them. Finally, the requested validation is performed for verifying there are no cameras
     * which have taken more than 10 times photos than the other cameras, for this, I'm using the largest photo quantity,
     * if this number greater than 10x the amount of photos from any of the other cameras, then the validation fails.
     */
    @Test
    public void test_4(){
        Map<String, Integer> map = new HashMap<>();
        List<String> cameras = photoList.stream().map(x -> x.getCamera().getName()).distinct().toList();
        for (String camera : cameras){
            map.put(camera, 0);
        }
        for (Photo p : photoList) {
            map.put(p.getCamera().getName(), map.get(p.getCamera().getName()) +1);
        }
        List<Integer> photoQuantityList = new ArrayList<>(map.values());
        int largestPhotoCount = Collections.max(photoQuantityList);
        photoQuantityList.remove(Integer.valueOf(largestPhotoCount));
        Assert.assertTrue(photoQuantityList.stream().noneMatch(x -> 10 * x < largestPhotoCount),
                "Validation failed: A camera which has taken more than 10x times photos than any of the rest was found");
    }
}
