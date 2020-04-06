package com.interviewproject.deals.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RootObject {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("data")
    @Expose
    private List<Deals> data = null;
    @SerializedName("type")
    @Expose
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Deals> getData() {
        return data;
    }

    public void setData(List<Deals> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
