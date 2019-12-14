package com.lc.bangumidemo.Sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Bookselect {

    public static Nvdetil select(MyDatabaseHelper dbhelper, Nvdetil data){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("Category", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                int pagesize = cursor.getInt(cursor.getColumnIndex("pagesize"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                int contentindex = cursor.getInt(cursor.getColumnIndex("contentindex"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                if((data.getAuthor().equals(author))&&(data.getBookname().equals(bookname)))
                {
                    Nvdetil returnbook = new Nvdetil(id,bookname,author,pagesize,pageindex,contentindex,content);
                    return returnbook;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }
    public static Nvdetil select(Bookindexdatabase dbhelper, Nvdetil data){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("Geegory", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                int pagesize = cursor.getInt(cursor.getColumnIndex("pagesize"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                int contentindex = cursor.getInt(cursor.getColumnIndex("contentindex"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                if((data.getBookname().equals(bookname)&&(data.getPageindex()==pageindex)))
                {
                    Nvdetil returnbook = new Nvdetil(id,bookname,author,pagesize,pageindex,contentindex,content);
                    return returnbook;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }
    public static Nvdetil selectisexit(Bookindexdatabase dbhelper, Nvdetil data){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("Geegory", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                int pagesize = cursor.getInt(cursor.getColumnIndex("pagesize"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                int contentindex = cursor.getInt(cursor.getColumnIndex("contentindex"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                if((data.getBookname().equals(bookname)&&(data.getPageindex()==pageindex)))
                {
                    Nvdetil returnbook = new Nvdetil(id,bookname,author,pagesize,pageindex,contentindex,content);
                    return returnbook;

                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }




    public static void selectindex(MyDatabaseHelper dbhelper){
        int i=0;
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("Category", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                int pagesize = cursor.getInt(cursor.getColumnIndex("pagesize"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                int contentindex = cursor.getInt(cursor.getColumnIndex("contentindex"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                Log.d("Index", "book name is " + bookname);
                Log.d("Index", "book author is " + author);
                Log.d("Index", "book pagesize is " + pagesize);
                Log.d("Index", "book pageindex is " + pageindex);
                Log.d("Index", "book contentindex is " + contentindex);
                Log.d("Index", "book content is " + content);
               i++;
                Log.d("Index size", " " + i);

            }while (cursor.moveToNext());
        }
        cursor.close();
    }
    public static void selectdata(Bookindexdatabase dbhelper){
        int i=0;
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("Geegory", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                int pagesize = cursor.getInt(cursor.getColumnIndex("pagesize"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                int contentindex = cursor.getInt(cursor.getColumnIndex("contentindex"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                Log.d("Bookdata", "book name is " + bookname);
                Log.d("Bookdata", "book author is " + author);
                Log.d("Bookdata", "book pagesize is " + pagesize);
                Log.d("Bookdata", "book pageindex is " + pageindex);
                Log.d("Bookdata", "book contentindex is " + contentindex);
                Log.d("Bookdata", "book content is " + content);
                i++;
                Log.d("bookstore", "book store is " + i);
            }while (cursor.moveToNext());
        }
        cursor.close();

    }
    public static void selectall(MyDatabaseHelper myDatabaseHelper,Bookindexdatabase bookindexdatabase){
        //selectdata(bookindexdatabase);
        selectindex(myDatabaseHelper);
    }
}
