package com.example.getcounrtyappretrofitexample;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.getcounrtyappretrofitexample.Adapters.Item_List_View_Adapter;
import com.example.getcounrtyappretrofitexample.Adapters.shopping_Cart_Adapter;
import com.example.getcounrtyappretrofitexample.Database_packge.sqliteHelper;
import com.example.getcounrtyappretrofitexample.models.Item;

import java.util.ArrayList;

public class Sopping_cart_Activity extends AppCompatActivity
{
    ArrayList<Item> ItemsList;
    shopping_Cart_Adapter ItemListAdapter = null;
    LinearLayoutManager layoutManager;
    RecyclerView ItemListView;
    Toolbar listToolbar;
    public  static sqliteHelper sqlHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sopping_cart_);

        listToolbar = findViewById(R.id.list_view_toolbar_cart);
        setSupportActionBar(listToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        ItemsList = new ArrayList<>();
        ItemListAdapter = new shopping_Cart_Adapter(Sopping_cart_Activity.this , R.layout.one_shopping_cart_item , ItemsList);
        layoutManager = new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.VERTICAL , false);
        ItemListView = findViewById(R.id.recycler_View_item_cart);
        ItemListView.setHasFixedSize(true);
        ItemListView.setItemAnimator(new DefaultItemAnimator());
        ItemListView.setLayoutManager(layoutManager);

        ItemListView.setAdapter(ItemListAdapter);
        ItemListAdapter.notifyDataSetChanged();


        sqlHelper = new sqliteHelper(this , "StoreAppDB.sqlite" , null ,1);


        //get items from database-------------------------------------------


        try
        {
            Cursor itemCursor = sqlHelper.getData("SELECT * FROM Items");
            ItemsList.clear();
            if (itemCursor.moveToFirst()) {
                do{
                    int item_id = itemCursor.getInt(0);
                    String item_name = itemCursor.getString(1);
                    String item_price = itemCursor.getString(2);
                    byte[] item_icon = itemCursor.getBlob(3);
                    int item_count = itemCursor.getInt(4);
                    ItemsList.add(new Item(item_id, item_name, item_price , item_icon , item_count));
                }while (itemCursor.moveToNext());
            }
            //----------------------------------------
        }
        catch (Exception e)
        {

        }


    }

    @Override
    protected void onStart()
    {
        ItemListAdapter.notifyDataSetChanged();
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            SendToItemActiviy();
        }

        return super.onOptionsItemSelected(item);
    }

    private void SendToItemActiviy()
    {
        Intent ItemsIntent = new Intent(Sopping_cart_Activity.this , Item_List_Activity.class);
        startActivity(ItemsIntent);
    }
}
