package com.example.getcounrtyappretrofitexample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item_cost
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("shipping_time")
    @Expose
    private String shippingTime;
    @SerializedName("mincharge")
    @Expose
    private String mincharge;
    @SerializedName("delivery_cost")
    @Expose
    private String deliveryCost;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }

    public String getMincharge() {
        return mincharge;
    }

    public void setMincharge(String mincharge) {
        this.mincharge = mincharge;
    }

    public String getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(String deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
