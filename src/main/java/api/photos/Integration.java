package api.photos;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.PropertiesLoader;

import java.util.HashMap;
import java.util.Map;

public class Integration {
    static PropertiesLoader reader;

    /**
     * API Rest Get Method that returns all photos from a given vehicle and date
     * Date type is specified by parameter, if empty, defaults to Martian Sol, otherwise, the earth date will be
     * used
     * @param earthDate
     * @return Response
     */
    public static Response getData(String earthDate) {
        reader = new PropertiesLoader("src/main/resources/test_data.properties");

        Map<String, String> params = new HashMap<>();
        params.put("api_key",reader.properties().getProperty("API_KEY"));
        if(!earthDate.isEmpty()){
            params.put("earth_date", earthDate);
        } else {
            params.put("sol", reader.properties().getProperty("SOL"));
        }
        RequestSpecification s = new RequestSpecBuilder().addParams(params).build();

        return RestAssured.
                given().pathParam("vehicle", reader.properties().getProperty("VEHICLE"))
                .spec(s)
                .when().get(reader.properties().getProperty("PHOTOS_URI"));
    }
}
