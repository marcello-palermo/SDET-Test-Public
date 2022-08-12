package utils;

import api.photos.models.PhotoResponseDTO;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

public class Generic {

    /**
     * Method that returns the PhotoResponseDTO object corresponding to the request response
     * @param resp
     * @return
     */
    public static PhotoResponseDTO getResponseAsList(Response resp){
        Gson g = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        return g.fromJson(resp.asString(), PhotoResponseDTO.class);
    }
}
