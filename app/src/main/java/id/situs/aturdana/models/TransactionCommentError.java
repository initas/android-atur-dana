package id.situs.aturdana.models;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class TransactionCommentError {

    @SerializedName("description")
    @Expose
    private List<String> descriptions = new ArrayList<String>();

    /**
     * @return The errors
     */
    public List<String> getDescriptions() {
        return descriptions;
    }
}
