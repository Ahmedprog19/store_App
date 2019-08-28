package com.example.getcounrtyappretrofitexample.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.getcounrtyappretrofitexample.Database_packge.sqliteHelper;
import com.example.getcounrtyappretrofitexample.R;
import com.example.getcounrtyappretrofitexample.models.Item;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Items_Grid_Adapter extends BaseAdapter
{
    private Context context;
    private int layout;
    private ArrayList<Item> ItemList;
    int ItemCount ;
    public static sqliteHelper sqlHelper;



    public Items_Grid_Adapter(Context context, int layout, ArrayList<Item> itemList)
    {
        this.context = context;
        this.layout = layout;
        ItemList = itemList;
    }

    @Override
    public int getCount()
    {
        return ItemList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return ItemList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    private class ViewHolder
    {
        ImageView ItemImage;
        TextView ItemName , ItemPrice , ItemCounter;
        Button Item_Add_Btn , Item_Increment_Btn , Item_Decrement_Btn;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
      ViewHolder holder = new ViewHolder();

        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout , null);

            holder.ItemImage = row.findViewById(R.id.Item_Image_Grid_View);
            holder.ItemName = row.findViewById(R.id.Item_Name_Grid_View);
            holder.ItemPrice = row.findViewById(R.id.Item_Price_Grid_View);
            holder.Item_Increment_Btn = row.findViewById(R.id.Item_Increment_Btn_Grid_View);
            holder.Item_Decrement_Btn = row.findViewById(R.id.Item_Decrement_Btn_Grid_View);
            holder.ItemCounter = row.findViewById(R.id.Item_Counter_Grid_View);
            holder.Item_Add_Btn = row.findViewById(R.id.Item_Add_Btn_Grid_View);
            row.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }

        final Item item = ItemList.get(position);

        Glide.with(context)
                .asBitmap()
                .load(item.getImage())
                .into(holder.ItemImage);

        holder.ItemName.setText(item.getEnName());
        holder.ItemPrice.setText(item.getPrice());

        final ViewHolder finalHolder = holder;
        holder.Item_Increment_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ItemCount = item.getItemCount() + 1;
                item.setItemCount(ItemCount);
                finalHolder.ItemCounter.setText(item.getItemCount()+"");

            }
        });

        holder.Item_Decrement_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(item.getItemCount() <= 0)
                {
                    item.setItemCount(0);
                    finalHolder.ItemCounter.setText(item.getItemCount()+"");


                }
                else
                {
                    ItemCount = item.getItemCount() - 1;
                    item.setItemCount(ItemCount);
                    finalHolder.ItemCounter.setText(item.getItemCount()+"");

                }
            }
        });

        holder.ItemCounter.setText(item.getItemCount()+"");

        final ViewHolder finalHolder1 = holder;
        holder.Item_Add_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqlHelper = new sqliteHelper(context , "StoreAppDB.sqlite" , null ,1);
                sqlHelper.queryData("CREATE TABLE IF NOT EXISTS Items (ItemId INTEGER PRIMARY KEY AUTOINCREMENT , ItemName VARCHAR ,ItemPrice VARCHAR , ItemImage BLOG , ItemCount INTEGER)");

                try
                {
                    sqlHelper.inserNewItemData
                            (
                                    item.getEnName(),
                                    item.getPrice(),
                                    imageViewToByte(finalHolder1.ItemImage),
                                    item.getItemCount()
                            );

                    Toast.makeText(context, "Item Added to shopping cart", Toast.LENGTH_SHORT).show();
                    finalHolder1.ItemCounter.setText(0+"");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });




        return row;
    }

    private byte[] imageViewToByte(ImageView image)
    {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG , 100 , stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
