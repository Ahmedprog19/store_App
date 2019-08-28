package com.example.getcounrtyappretrofitexample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("en_name")
    @Expose
    private String enName;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("en_details")
    @Expose
    private String enDetails;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("categories_id")
    @Expose
    private String categoriesId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public byte[] getItemImageSql()
    {
        return itemImageSql;
    }

    public void setItemImageSql(byte[] itemImageSql)
    {
        this.itemImageSql = itemImageSql;
    }

    private byte [] itemImageSql;

    public Item(Integer id, String enName, String price, byte[] itemImageSql, int itemCount) {
        this.id = id;
        this.enName = enName;
        this.price = price;
        this.itemImageSql = itemImageSql;
        ItemCount = itemCount;
    }

    public int getItemCount() {
        return ItemCount;
    }

    public void setItemCount(int itemCount) {
        ItemCount = itemCount;
    }

    private int ItemCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }
    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEnDetails() {
        return enDetails;
    }

    public void setEnDetails(String enDetails) {
        this.enDetails = enDetails;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
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

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }


}
