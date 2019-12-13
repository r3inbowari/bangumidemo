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
                Log.d("MainActivity", "book name is " + bookname);
                Log.d("MainActivity", "book author is " + author);
                Log.d("MainActivity", "book pagesize is " + pagesize);
                Log.d("MainActivity", "book pageindex is " + pageindex);
                Log.d("MainActivity", "book contentindex is " + contentindex);
                Log.d("MainActivity", "book content is " + content);
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
                Log.d("MainActivity", "book name is " + bookname);
                Log.d("MainActivity", "book author is " + author);
                Log.d("MainActivity", "book pagesize is " + pagesize);
                Log.d("MainActivity", "book pageindex is " + pageindex);
                Log.d("MainActivity", "book contentindex is " + contentindex);
                Log.d("MainActivity", "book content is " + content);
                if((data.getAuthor().equals(author))&&(data.getBookname().equals(bookname)&&(data.getPageindex()==pageindex)))
                {
                    Nvdetil returnbook = new Nvdetil(id,bookname,author,pagesize,pageindex,contentindex,content);
                    return returnbook;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }
}
