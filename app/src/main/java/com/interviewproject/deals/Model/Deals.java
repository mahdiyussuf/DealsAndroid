package com.interviewproject.deals.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Deals {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("aisle")
    @Expose
    private String aisle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("salePrice")
    @Expose
    private String salePrice;
    @SerializedName("title")
    @Expose
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
