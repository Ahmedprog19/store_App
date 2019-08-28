package com.example.getcounrtyappretrofitexample;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.example.getcounrtyappretrofitexample.Adapters.Items_Grid_Adapter;
import com.example.getcounrtyappretrofitexample.models.Item;
import com.example.getcounrtyappretrofitexample.models.Item_content;
import com.example.getcounrtyappretrofitexample.network.Api_interface2;
import com.example.getcounrtyappretrofitexample.network.RetrofitClient2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Item_Grid_Activity extends AppCompatActivity
{
    GridView ItemGridView;
    LinearLayoutManager layoutManager;
    ArrayList<Item> Item_List;
    Toolbar gridToolbar;


    Items_Grid_Adapter ItemAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__grid_);

        //navigationView =findViewById(R.id.nav_view);






        gridToolbar = findViewById(R.id.grid_view_toolbar);
        setSupportActionBar(gridToolbar);

        ItemGridView = findViewById(R.id.Items_GridView);
        Item_List = new ArrayList<>();

        ItemAdapter = new Items_Grid_Adapter(this , R.layout.one_item_grid_layout , Item_List);

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
                    Item_List.clear();

                    Item_List.addAll(item_content.get(0).getItems());
                    ItemGridView.setAdapter(ItemAdapter);
                    ItemAdapter.notifyDataSetChanged();

                }
                else
                {
                    int statusCode = response.code();
                    String messageCode = response.message();

                    Toast.makeText(Item_Grid_Activity.this, "code : " + String.valueOf(statusCode), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Item_Grid_Activity.this, "Message : " + messageCode, Toast.LENGTH_SHORT).show();
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
        ItemAdapter.notifyDataSetChanged();
        super.onStart();
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
            Intent listIntent = new Intent(Item_Grid_Activity.this , Item_List_Activity.class);
            startActivity(listIntent);
        }
        else if(id == R.id.gridViewToolbar)
        {
         return true;
        }
        else if(id == R.id.shopping_cart)
        {
            Intent shoppingCartIntent = new Intent(Item_Grid_Activity.this , Sopping_cart_Activity.class);
            startActivity(shoppingCartIntent);
        }

        return true;
    }
}
