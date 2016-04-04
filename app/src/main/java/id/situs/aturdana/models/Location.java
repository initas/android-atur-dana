
package id.situs.aturdana.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Location {

    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("altitude")
    @Expose
    private Object altitude;

    /**
     * 
     * @return
     *     The name
     */
    public Object getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(Object name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    public Object getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public Object getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The altitude
     */
    public Object getAltitude() {
        return altitude;
    }

    /**
     * 
     * @param altitude
     *     The altitude
     */
    public void setAltitude(Object altitude) {
        this.altitude = altitude;
    }

}
