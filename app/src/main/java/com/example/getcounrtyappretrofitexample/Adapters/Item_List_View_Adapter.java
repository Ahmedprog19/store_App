package com.example.getcounrtyappretrofitexample.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Item_List_View_Adapter extends RecyclerView.Adapter<Item_List_View_Adapter.MyViewHolder>
{
    private ArrayList<Item> Items_List = new ArrayList<>();
    Context mContext;
    private int layout;
    int ItemCount ;

   public static sqliteHelper sqlHelper;




    public Item_List_View_Adapter(Context mContext, int layout ,ArrayList<Item> items_List)
    {
        Items_List = items_List;
        this.mContext = mContext;
        this.layout = layout;
    }

    @NonNull
    @Override
    public Item_List_View_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout , parent , false);

        Item_List_View_Adapter.MyViewHolder holder = new Item_List_View_Adapter.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position)
    {
        final Item item = Items_List.get(position);


        Glide.with(mContext)
                .asBitmap()
                .load(item.getImage())
                .into(holder.Item_Image);

        holder.Item_Name.setText(item.getEnName());
        holder.Item_Price.setText(item.getPrice());
        holder.Item_Increment_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ItemCount = item.getItemCount() + 1;
                item.setItemCount(ItemCount);
                holder.Item_Counter.setText(item.getItemCount()+"");


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
                    holder.Item_Counter.setText(item.getItemCount()+"");

                }
                else
                {
                    ItemCount = item.getItemCount() - 1;
                    item.setItemCount(ItemCount);
                    holder.Item_Counter.setText(item.getItemCount()+"");
                }

            }
        });

        holder.Item_Add_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sqlHelper = new sqliteHelper(mContext , "StoreAppDB.sqlite" , null ,1);
                sqlHelper.queryData("CREATE TABLE IF NOT EXISTS Items (ItemId INTEGER PRIMARY KEY AUTOINCREMENT , ItemName VARCHAR ,ItemPrice VARCHAR , ItemImage BLOG , ItemCount INTEGER)");

                try
                {
                    sqlHelper.inserNewItemData
                            (
                                    item.getEnName(),
                                    item.getPrice(),
                                    imageViewToByte(holder.Item_Image),
                                    item.getItemCount()
                            );

                    Toast.makeText(mContext, "Item Added to shopping cart", Toast.LENGTH_SHORT).show();
                    holder.Item_Counter.setText(0+"");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

    }



    @Override
    public int getItemCount()
    {
        return Items_List.size();
    }

    private byte[] imageViewToByte(ImageView image)
    {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG , 100 , stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView Item_Image;
        TextView Item_Name , Item_Price , Item_Counter;
        Button Item_Add_Btn , Item_Increment_Btn , Item_Decrement_Btn;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            Item_Image = itemView.findViewById(R.id.Item_Image_List_View);
            Item_Name = itemView.findViewById(R.id.Item_Name_List_View);
            Item_Price = itemView.findViewById(R.id.Item_Price_List_View);
            Item_Counter = itemView.findViewById(R.id.Item_Counter_List_View);
            Item_Add_Btn = itemView.findViewById(R.id.Item_Add_Btn_List_View);
            Item_Increment_Btn = itemView.findViewById(R.id.Item_Increment_Btn_List_View);
            Item_Decrement_Btn = itemView.findViewById(R.id.Item_Decrement_Btn_List_View);

        }
    }
}
