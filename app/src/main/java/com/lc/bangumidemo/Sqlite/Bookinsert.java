package com.lc.bangumidemo.Sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Bookinsert {
    public static void insertindex(MyDatabaseHelper dbhelper, Bookindexclass data){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //开始组装第一条数据
        values.put("author", data.getAuthor());
        values.put("bookname", data.getBookname());
        values.put("hardpageindex",data.getHardpageindex());
        values.put("hardcontentindex", data.getHardcontentindex());
        values.put("pagecount", data.getPagecount());
        values.put("pageindex", data.getPageindex());
        values.put("contentindex", data.getContentindex());
        db.insert("BookIndex",null,values);//插入第一条数据
        values.clear();//！！！！！
    }

    /**
     * data class BookDataclass (
     *     val author:String,
     *     val bookname:String,
     *     val pagecount:Int,
     *     val pageindex:Int,
     *     val contentindex:Int
     *
     * )
     * @param dbhelper
     * @param data
     */
    public static void insertbookdata(MyDatabaseHelper dbhelper, BookDataclass data){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //开始组装第一条数据
        values.put("author", data.getAuthor());
        values.put("bookname", data.getBookname());
        values.put("pagecount",data.getPagecount());
        values.put("pageindex", data.getPageindex());
        values.put("content", data.getContent());
        db.insert("BookData",null,values);//插入第一条数据
        values.clear();//！！！！！
    }

    /**
     *   val CREATE_BOOKREAD = ("create table BookRead ("
     *                 + "id integer primary key autoincrement, "
     *                 + "author text, "
     *                 + "bookname text, "
     *                 + "pagecount integer, "
     *                 + "data text, "
     *                 + "pageindex interger, "
     *                 + "contentindex interger, "
     *                 + "start interger, "
     *                 + "end interger, "
     *                 + "index interger)"
     *                 )
     * @param dbhelper
     * @param data
     */
    public static void insertbookread(MyDatabaseHelper dbhelper, Bookreadclass data){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //开始组装第一条数据
        values.put("author", data.getAuthor());
        values.put("bookname", data.getBookname());
        values.put("pagecount",data.getPagecount());
        values.put("bookdata", data.getBookdata());
        values.put("pageindex", data.getPageindex());
        values.put("contentindex", data.getContentindex());
        values.put("start", data.getStart());
        values.put("end", data.getEnd());
        values.put("indexx", data.getIndexx());
        db.insert("BookRead",null,values);//插入第一条数据
        values.clear();//！！！！！

    }
}
