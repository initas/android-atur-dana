
package id.situs.aturdana.models;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class LoggedOnUser {

    @SerializedName("is_pinned")
    @Expose
    private Boolean isPinned;

    /**
     * @return The isPinned
     */
    public Boolean getIsPinned() {
        return isPinned;
    }

    /**
     * @param isPinned The isPinned
     */
    public void setIsPinned(Boolean isPinned) {
        this.isPinned = isPinned;
    }

}
