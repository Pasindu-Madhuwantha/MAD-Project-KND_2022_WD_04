package com.example.kcc_multiplex;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String MovieName,String MovieType,String Hours,String Status,byte[] movieimage,byte[] coverimage,String Description){
        SQLiteDatabase database=getWritableDatabase();
        String sql="INSERT INTO MOVIES VALUES (NULL,?,?,?,?,?,?,?)";

        SQLiteStatement statement =database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,MovieName);
        statement.bindString(2,MovieType);
        statement.bindString(3,Hours);
        statement.bindString(4,Status);
        statement.bindBlob(5,movieimage);
        statement.bindBlob(6,coverimage);
        statement.bindString(7,Description);

        statement.executeInsert();
    }

    public void updateData(String MovieName,String MovieType,String Hours,String Status,byte[] movieimage,byte[] coverimage,String Description,int id){
        SQLiteDatabase database=getWritableDatabase();

        String sql="UPDATE MOVIES SET MovieName =?, MovieType =?, MovieHours =?, MoiveStatus =?, MovieImage =?, CoverImage =?, Description=? WHERE id=?";
        SQLiteStatement statement=database.compileStatement(sql);

        statement.bindString(1,MovieName);
        statement.bindString(2,MovieType);
        statement.bindString(3,Hours);
        statement.bindString(4,Status);
        statement.bindBlob(5,movieimage);
        statement.bindBlob(6,coverimage);
        statement.bindString(7,Description);
        statement.bindDouble(8,(double)id);

        statement.execute();
        database.close();

    }

    public void Deletedata(int id){
        SQLiteDatabase database=getWritableDatabase();

        String sql="DELETE FROM MOVIES WHERE id=?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1,(double)id);

        statement.execute();
        database.close();

    }




    public Cursor getData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
