package fr.unice.polytech.jsonelements;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "thumb",
        "small",
        "medium",
        "large"
})
public class Sizes_ {

    @JsonProperty("thumb")
    private Thumb_ thumb;
    @JsonProperty("small")
    private Small_ small;
    @JsonProperty("medium")
    private Medium___ medium;
    @JsonProperty("large")
    private Large_ large;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The thumb
     */
    @JsonProperty("thumb")
    public Thumb_ getThumb() {
        return thumb;
    }

    /**
     *
     * @param thumb
     * The thumb
     */
    @JsonProperty("thumb")
    public void setThumb(Thumb_ thumb) {
        this.thumb = thumb;
    }

    /**
     *
     * @return
     * The small
     */
    @JsonProperty("small")
    public Small_ getSmall() {
        return small;
    }

    /**
     *
     * @param small
     * The small
     */
    @JsonProperty("small")
    public void setSmall(Small_ small) {
        this.small = small;
    }

    /**
     *
     * @return
     * The medium
     */
    @JsonProperty("medium")
    public Medium___ getMedium() {
        return medium;
    }

    /**
     *
     * @param medium
     * The medium
     */
    @JsonProperty("medium")
    public void setMedium(Medium___ medium) {
        this.medium = medium;
    }

    /**
     *
     * @return
     * The large
     */
    @JsonProperty("large")
    public Large_ getLarge() {
        return large;
    }

    /**
     *
     * @param large
     * The large
     */
    @JsonProperty("large")
    public void setLarge(Large_ large) {
        this.large = large;
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