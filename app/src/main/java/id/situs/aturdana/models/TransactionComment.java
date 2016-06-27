
package id.situs.aturdana.models;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class TransactionComment extends Wrapper {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("updated_at")
    @Expose
    private int updatedAt;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("data")
    @Expose
    private List<TransactionComment> data = new ArrayList<TransactionComment>();
    @SerializedName("errors")
    @Expose
    private TransactionCommentError errors;

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The updatedAt
     */
    public int getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The user
     */
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * @return The data
     */
    public List<TransactionComment> getData() {
        return data;
    }

    /**
     * @param sources The sources
     */
    public void setData(List<TransactionComment> data) {
        this.data = data;
    }

    /**
     * @return The errors
     */
    public TransactionCommentError getErrors() {
        return errors;
    }

    /**
     * @param user The errors
     */
    public void setErrors(TransactionCommentError errors) {
        this.errors = errors;
    }
}
