package com.example.getcounrtyappretrofitexample.Database_packge;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class sqliteHelper extends SQLiteOpenHelper
{

    public sqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }


    public void queryData(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }



    public void deleteItemRecord(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CATEGORY_ITEMS", "ItemId" + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }




    public void inserNewItemData(String name , String price  , byte [] image , int count)
    {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Items VALUES (NULL , ? , ? , ? ,  "+count+" ) ";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1 , name);
        statement.bindString(2, price);
        statement.bindBlob(3 , image);
        statement.executeInsert();

    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql , null);

    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
