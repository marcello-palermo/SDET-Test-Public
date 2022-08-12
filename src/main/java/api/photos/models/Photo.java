package api.photos.models;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "sol",
    "camera",
    "img_src",
    "earth_date",
    "rover"
})
@Generated("jsonschema2pojo")
public class Photo {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("sol")
    private Integer sol;
    @JsonProperty("camera")
    private Camera camera;
    @JsonProperty("img_src")
    private String imgSrc;
    @JsonProperty("earth_date")
    private String earthDate;
    @JsonProperty("rover")
    private Rover rover;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("sol")
    public Integer getSol() {
        return sol;
    }

    @JsonProperty("sol")
    public void setSol(Integer sol) {
        this.sol = sol;
    }

    @JsonProperty("camera")
    public Camera getCamera() {
        return camera;
    }

    @JsonProperty("camera")
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @JsonProperty("img_src")
    public String getImgSrc() {
        return imgSrc;
    }

    @JsonProperty("img_src")
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    @JsonProperty("earth_date")
    public String getEarthDate() {
        return earthDate;
    }

    @JsonProperty("earth_date")
    public void setEarthDate(String earthDate) {
        this.earthDate = earthDate;
    }

    @JsonProperty("rover")
    public Rover getRover() {
        return rover;
    }

    @JsonProperty("rover")
    public void setRover(Rover rover) {
        this.rover = rover;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
