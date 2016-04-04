package id.situs.aturdana.models;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Source {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon_class")
    @Expose
    private String iconClass;
    @SerializedName("hex_color")
    @Expose
    private String hexColor;
    @SerializedName("updated_at")
    @Expose
    private int updatedAt;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("transaction_count")
    @Expose
    private int transactionCount;
    @SerializedName("collaborator_count")
    @Expose
    private int collaboratorCount;
    @SerializedName("collaborators")
    @Expose
    private List<User> users = new ArrayList<User>();

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
     * @return The userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId The user_id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The iconClass
     */
    public String getIconClass() {
        return "{" + iconClass.replace("fa ", "") + "}";
    }

    /**
     * @param iconClass The icon_class
     */
    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    /**
     * @return The hexColor
     */
    public String getHexColor() {
        return hexColor;
    }

    /**
     * @param hexColor The hex_color
     */
    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
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
     * @return The amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return The transactionCount
     */
    public int getTransactionCount() {
        return transactionCount;
    }

    /**
     * @param transactionCount The transaction_count
     */
    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    /**
     * @return The collaboratorCount
     */
    public int getCollaboratorCount() {
        return collaboratorCount;
    }

    /**
     * @param collaboratorCount The collaborator_count
     */
    public void setCollaboratorCount(int collaboratorCount) {
        this.collaboratorCount = collaboratorCount;
    }

    /**
     * @return The users
     */
    public List<User> getCollaborators() { return users; }

    /**
     * @param users The users
     */
    public void setCollaborators(List<User> users) {
        this.users = users;
    }
}