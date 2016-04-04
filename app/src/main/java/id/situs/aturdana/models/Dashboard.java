
package id.situs.aturdana.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Dashboard {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("transactions")
    @Expose
    private List<Transaction> transactions = new ArrayList<Transaction>();
    @SerializedName("sources")
    @Expose
    private List<Source> sources = new ArrayList<Source>();
    @SerializedName("total_amount")
    @Expose
    private int totalAmount;

    /**
     * 
     * @return
     *     The status
     */
    public int getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * 
     * @param transactions
     *     The transactions
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * 
     * @return
     *     The sources
     */
    public List<Source> getSources() {
        return sources;
    }

    /**
     * 
     * @param sources
     *     The sources
     */
    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    /**
     * 
     * @return
     *     The totalAmount
     */
    public int getTotalAmount() {
        return totalAmount;
    }

    /**
     * 
     * @param totalAmount
     *     The total_amount
     */
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

}
