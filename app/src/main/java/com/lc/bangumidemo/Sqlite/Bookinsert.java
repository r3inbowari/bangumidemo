package com.lc.bangumidemo.Sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Bookinsert {
        public static void insert(Bookindexdatabase dbhelper, Nvdetil data){
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            //开始组装第一条数据
            values.put("author", data.getAuthor());
            values.put("bookname", data.getBookname());
            values.put("pagesize",data.getPagesize());
            values.put("pageindex", data.getPageindex());
            values.put("contentindex", data.getContentindex());
            values.put("content", data.getContent());
            db.insert("Geegory",null,values);//插入第一条数据
            values.clear();//！！！！！
        }
    public static void insert(MyDatabaseHelper dbhelper, Nvdetil data){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //开始组装第一条数据
        values.put("author", data.getAuthor());
        values.put("bookname", data.getBookname());
        values.put("pagesize",data.getPagesize());
        values.put("pageindex", data.getPageindex());
        values.put("contentindex", data.getContentindex());
        values.put("content", data.getContent());
        db.insert("Category",null,values);//插入第一条数据
        values.clear();//！！！！！
    }
}

