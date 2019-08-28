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

public class shopping_Cart_Adapter extends RecyclerView.Adapter<shopping_Cart_Adapter.MyViewHolder>
{
    private ArrayList<Item> Items_List = new ArrayList<>();
    Context mContext;
    private int layout;
    int ItemCount ;





    public shopping_Cart_Adapter(Context mContext, int layout ,ArrayList<Item> items_List)
    {
        Items_List = items_List;
        this.mContext = mContext;
        this.layout = layout;
    }




    @NonNull
    @Override
    public shopping_Cart_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout , parent , false);

        shopping_Cart_Adapter.MyViewHolder holder = new shopping_Cart_Adapter.MyViewHolder(view);

        return holder;
        }


    public void onBindViewHolder(@NonNull final shopping_Cart_Adapter.MyViewHolder holder, int position)
    {
        final Item item = Items_List.get(position);


        Glide.with(mContext)
                .asBitmap()
                .load(item.getItemImageSql())
                .into(holder.Item_Image);

        holder.Item_Name.setText(item.getEnName());
        holder.Item_Price.setText(item.getPrice());
        holder.Item_Counter.setText(item.getItemCount()+"");
        holder.Item_Total_Price.setText(item.getItemCount() * Integer.parseInt(item.getPrice())+ "") ;
        holder.Item_Increment_Btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ItemCount = item.getItemCount() + 1;
                item.setItemCount(ItemCount);
                holder.Item_Counter.setText(item.getItemCount()+"");
                holder.Item_Total_Price.setText(item.getItemCount() * Integer.parseInt(item.getPrice())+ "") ;


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
                    holder.Item_Total_Price.setText(item.getItemCount() * Integer.parseInt(item.getPrice())+ "") ;
                }
                else
                {
                    ItemCount = item.getItemCount() - 1;
                    item.setItemCount(ItemCount);
                    holder.Item_Counter.setText(item.getItemCount()+"");
                    holder.Item_Total_Price.setText(item.getItemCount() * Integer.parseInt(item.getPrice())+ "") ;

                }

            }
        });


    }

    @Override
    public int getItemCount()
    {
      return Items_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView Item_Image;
        TextView Item_Name , Item_Price , Item_Counter , Item_Total_Price;
        Button Item_Increment_Btn , Item_Decrement_Btn;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            Item_Image = itemView.findViewById(R.id.Item_Image_List_View_shopping);
            Item_Name = itemView.findViewById(R.id.Item_Name_List_View_shopping);
            Item_Price = itemView.findViewById(R.id.Item_Price_List_View_shopping);
            Item_Counter = itemView.findViewById(R.id.Item_Counter_List_View_s);
            Item_Increment_Btn = itemView.findViewById(R.id.Item_Increment_Btn_List_View_s);
            Item_Decrement_Btn = itemView.findViewById(R.id.Item_Decrement_Btn_List_View_s);
            Item_Total_Price = itemView.findViewById(R.id.total_price_view_shopping);

        }
    }
}
