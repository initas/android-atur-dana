package id.situs.aturdana;

/**
 * Created by MF on 3/20/16.
 */
public class TransactionInfo {
    private String name;
    private String imageUrl;
    private String title;
    private String amount;
    private String timestamp;
    private String categoryName;
    private String categoryIconClass;
    private String categoryHexColor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIconClass() {
        return categoryIconClass;
    }

    public void setCategoryIconClass(String categoryIconClass) {
        this.categoryIconClass = categoryIconClass;
    }

    public String getCategoryHexColor() {
        return categoryHexColor;
    }

    public void setCategoryHexColor(String categoryHexColor) {
        this.categoryHexColor = categoryHexColor;
    }
}
