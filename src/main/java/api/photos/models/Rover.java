package api.photos.models;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "landing_date",
    "launch_date",
    "status"
})
@Generated("jsonschema2pojo")
public class Rover {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("landing_date")
    private String landingDate;
    @JsonProperty("launch_date")
    private String launchDate;
    @JsonProperty("status")
    private String status;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("landing_date")
    public String getLandingDate() {
        return landingDate;
    }

    @JsonProperty("landing_date")
    public void setLandingDate(String landingDate) {
        this.landingDate = landingDate;
    }

    @JsonProperty("launch_date")
    public String getLaunchDate() {
        return launchDate;
    }

    @JsonProperty("launch_date")
    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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
