
package id.situs.aturdana.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Image {

    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("on_demand")
    @Expose
    private String onDemand;

    /**
     * 
     * @return
     *     The original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * 
     * @param original
     *     The original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * 
     * @return
     *     The onDemand
     */
    public String getOnDemand() {
        return onDemand;
    }

    /**
     * 
     * @param onDemand
     *     The on_demand
     */
    public void setOnDemand(String onDemand) {
        this.onDemand = onDemand;
    }

}
