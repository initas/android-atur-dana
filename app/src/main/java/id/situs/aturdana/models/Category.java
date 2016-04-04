
package id.situs.aturdana.models;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Category {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("hex_color")
    @Expose
    private String hexColor;
    @SerializedName("icon_class")
    @Expose
    private String iconClass;

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

}
