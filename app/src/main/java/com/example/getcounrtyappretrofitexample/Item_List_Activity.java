package com.example.getcounrtyappretrofitexample;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.getcounrtyappretrofitexample.Adapters.Item_List_View_Adapter;
import com.example.getcounrtyappretrofitexample.models.Item;
import com.example.getcounrtyappretrofitexample.models.Item_content;
import com.example.getcounrtyappretrofitexample.network.Api_interface2;
import com.example.getcounrtyappretrofitexample.network.RetrofitClient2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Item_List_Activity extends AppCompatActivity
{
    ArrayList<Item> ItemsList;
    Item_List_View_Adapter ItemListAdapter = null;
    LinearLayoutManager layoutManager;
    RecyclerView ItemListView;
    Toolbar listToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__list_);

        listToolbar = findViewById(R.id.list_view_toolbar);
        setSupportActionBar(listToolbar);



        //-----------------------------------------------------------------------



        ItemsList = new ArrayList<>();
        ItemListAdapter = new Item_List_View_Adapter(Item_List_Activity.this , R.layout.one_item_list_layout , ItemsList);
        layoutManager = new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.VERTICAL , false);
        ItemListView = findViewById(R.id.recycler_View_item);
        ItemListView.setHasFixedSize(true);
        ItemListView.setItemAnimator(new DefaultItemAnimator());
        ItemListView.setLayoutManager(layoutManager);


        returnAllItems();


    }

    public void returnAllItems()
    {
        Api_interface2 api_interface2 = RetrofitClient2.getClient().create(Api_interface2.class);
        Call<List<Item_content>> call = api_interface2.getItems();
        call.enqueue(new Callback<List<Item_content>>() {
            @Override
            public void onResponse(Call<List<Item_content>> call, Response<List<Item_content>> response)
            {
                if(response.isSuccessful())
                {
                    List<Item_content> item_content = response.body();
                    ItemsList.clear();

                    ItemsList.addAll(item_content.get(0).getItems());

                    ItemListView.setAdapter(ItemListAdapter);
                    ItemListAdapter.notifyDataSetChanged();

                }
                else
                {
                    int statusCode = response.code();
                    String messageCode = response.message();

                    Toast.makeText(Item_List_Activity.this, "code : " + String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Item_List_Activity.this, "Message : " + messageCode, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Item_content>> call, Throwable t)
            {
                Log.e("error message is" , t.getMessage() );
            }
        });


    }

    @Override
    protected void onStart()
    {
        super.onStart();
        ItemListAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.listViewToolbar)
        {
            return true;
        }
        else if(id == R.id.gridViewToolbar)
        {
            Intent gridIntent = new Intent(Item_List_Activity.this , Item_Grid_Activity.class);
            startActivity(gridIntent);
        }
        else if(id == R.id.shopping_cart)
        {
            Intent shoppingCartIntent = new Intent(Item_List_Activity.this , Sopping_cart_Activity.class);
            startActivity(shoppingCartIntent);
        }

     return true;
    }
}
