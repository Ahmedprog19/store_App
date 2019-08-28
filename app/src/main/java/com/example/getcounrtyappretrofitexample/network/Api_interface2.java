package com.example.getcounrtyappretrofitexample.network;

import com.example.getcounrtyappretrofitexample.models.Item_content;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api_interface2
{
    @GET("/~helix/items_with_cat_or_subcat/ar/9")
    Call<List<Item_content>> getItems();
}
