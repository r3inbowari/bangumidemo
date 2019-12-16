package com.lc.bangumidemo.Sqlite;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Bookselect {

    public static Bookindexclass selectindex(MyDatabaseHelper dbhelper, Selectclass data){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("BookIndex", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                int hardpageindex = cursor.getInt(cursor.getColumnIndex("hardpageindex"));
                int hardcontentindex = cursor.getInt(cursor.getColumnIndex("hardcontentindex"));
                int pagecount = cursor.getInt(cursor.getColumnIndex("pagecount"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                int contentindex = cursor.getInt(cursor.getColumnIndex("contentindex"));
                if((data.getAuthor().equals(author))&&(data.getBookname().equals(bookname))&&(data.getPagecount()==pagecount))
                {
                    Bookindexclass returnindex = new Bookindexclass(id,author,bookname,hardpageindex,hardcontentindex,pagecount,pageindex,contentindex);
                    return returnindex;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }
    public static BookDataclass selectbookdata(MyDatabaseHelper dbhelper, Selectclass data,int position){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("BookData", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                int contentsize = cursor.getInt(cursor.getColumnIndex("contentsize"));
                int pagecount = cursor.getInt(cursor.getColumnIndex("pagecount"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                if((data.getAuthor().equals(author))&&(data.getBookname().equals(bookname))&&(data.getPagecount()==pagecount)&&(pageindex==position))
                {
                    BookDataclass returnbookdataclass = new BookDataclass(author,bookname,content,contentsize,pagecount,pageindex);
                    return returnbookdataclass;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }

    /**
     *    val CREATE_BOOKREAD = ("create table BookRead ("
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
     * @param position
     * @return
     */
    public static Bookreadclass selectbookread(MyDatabaseHelper dbhelper, Selectclass data,int position){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("BookRead", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                int pagecount = cursor.getInt(cursor.getColumnIndex("pagecount"));
                String bookdata = cursor.getString(cursor.getColumnIndex("bookdata"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                int contentindex = cursor.getInt(cursor.getColumnIndex("contentindex"));
                int start = cursor.getInt(cursor.getColumnIndex("start"));
                int end = cursor.getInt(cursor.getColumnIndex("end"));
                int index = cursor.getInt(cursor.getColumnIndex("indexx"));
                if((data.getAuthor().equals(author))&&(data.getBookname().equals(bookname))&&(data.getPagecount()==pagecount)&&(pageindex==position))
                {
                    Bookreadclass returnbookdataclass = new Bookreadclass(author,bookname,pagecount,bookdata,pageindex,contentindex,start,end,index);
                    return returnbookdataclass;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }
    public static Bookreadclass selectbookreaddata(MyDatabaseHelper dbhelper, Selectclass data,int conindex){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("BookRead", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                int pagecount = cursor.getInt(cursor.getColumnIndex("pagecount"));
                String datatxt = cursor.getString(cursor.getColumnIndex("bookdata"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                int contentindex = cursor.getInt(cursor.getColumnIndex("contentindex"));
                int start = cursor.getInt(cursor.getColumnIndex("start"));
                int end = cursor.getInt(cursor.getColumnIndex("end"));
                int index = cursor.getInt(cursor.getColumnIndex("indexx"));
                if((data.getAuthor().equals(author))&&(data.getBookname().equals(bookname))&&(data.getPagecount()==pagecount)&&(index==conindex))
                {
                    Bookreadclass returnbookdataclass = new Bookreadclass(author,bookname,pagecount,datatxt,pageindex,contentindex,start,end,index);
                    return returnbookdataclass;
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return null;
    }
    @SuppressLint("LongLogTag")
    public static void selectalldata(MyDatabaseHelper dbhelper)
    {
        int i=0;
        int j=0;
        int k=0;
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor cursor = db.query("BookData", null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String author = cursor.getString(cursor.getColumnIndex("author") );
                String bookname = cursor.getString(cursor.getColumnIndex("bookname"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                int contentsize = cursor.getInt(cursor.getColumnIndex("contentsize"));
                int pagecount = cursor.getInt(cursor.getColumnIndex("pagecount"));
                int pageindex = cursor.getInt(cursor.getColumnIndex("pageindex"));
                j++;
                try {
                    Log.i("Bookdata:author", author);
                    Log.i("Bookdata:bookname", bookname);
                    Log.i("Bookdata:content", content);
                    Log.i("Bookdata:contentsize", new Integer(contentsize).toString());
                    Log.i("Bookdata:pagecount", new Integer(pagecount).toString());
                    Log.i("Bookdata:pageindex", new Integer(pageindex).toString());
                }catch (Exception e){
                    System.out.println(e);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        SQLiteDatabase db3 = dbhelper.getWritableDatabase();
        Cursor cursor3 = db3.query("BookRead", null,null,null,null,null,null);
        if (cursor3.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor3.getInt(cursor3.getColumnIndex("id"));
                String author = cursor3.getString(cursor3.getColumnIndex("author") );
                String bookname = cursor3.getString(cursor3.getColumnIndex("bookname"));
                int pagecount = cursor3.getInt(cursor3.getColumnIndex("pagecount"));
                String datatxt = cursor3.getString(cursor3.getColumnIndex("bookdata"));
                int pageindex = cursor3.getInt(cursor3.getColumnIndex("pageindex"));
                int contentindex = cursor3.getInt(cursor3.getColumnIndex("contentindex"));
                int start = cursor3.getInt(cursor3.getColumnIndex("start"));
                int end = cursor3.getInt(cursor3.getColumnIndex("end"));
                int index = cursor3.getInt(cursor3.getColumnIndex("indexx"));
                k++;
                try {
                    Log.i("BookRead:author", author);
                    Log.i("BookRead:bookname", bookname);
                    Log.i("BookRead:datatxt", datatxt);
                    Log.i("BookRead:pagecount", new Integer(pagecount).toString());
                    Log.i("BookRead:pageindex", new Integer(pageindex).toString());
                    Log.i("BookRead:contentindex", new Integer(contentindex).toString());
                    Log.i("BookRead:start", new Integer(start).toString());
                    Log.i("BookRead:end", new Integer(end).toString());
                    Log.i("BookRead:index", new Integer(index).toString());
                }catch (Exception e){
                    System.out.println(e);
                }
            }while (cursor3.moveToNext());
        }
        cursor3.close();
        SQLiteDatabase db2 = dbhelper.getWritableDatabase();
        Cursor cursor2 = db2.query("BookIndex", null,null,null,null,null,null);
        if (cursor2.moveToFirst()){
            do {
                //遍历Cursor对象，取出数据并打印
                int id = cursor2.getInt(cursor2.getColumnIndex("id"));
                String author = cursor2.getString(cursor2.getColumnIndex("author") );
                String bookname = cursor2.getString(cursor2.getColumnIndex("bookname"));
                int hardpageindex = cursor2.getInt(cursor2.getColumnIndex("hardpageindex"));
                int hardcontentindex = cursor2.getInt(cursor2.getColumnIndex("hardcontentindex"));
                int pagecount = cursor2.getInt(cursor2.getColumnIndex("pagecount"));
                int pageindex = cursor2.getInt(cursor2.getColumnIndex("pageindex"));
                int contentindex = cursor2.getInt(cursor2.getColumnIndex("contentindex"));
                i++;
                try {
                    Log.i("BookIndex:author", author);
                    Log.i("BookIndex:bookname", bookname);
                    Log.i("BookIndex:hardpageindex", new Integer(hardpageindex).toString());
                    Log.i("BookIndex:hardcontentindex", new Integer(hardcontentindex).toString());
                    Log.i("BookIndex:pagecount", new Integer(pagecount).toString());
                    Log.i("BookIndex:pageindex", new Integer(pageindex).toString());
                    Log.i("BookIndex:contentindex", new Integer(contentindex).toString());


                }catch (Exception e){
                    System.out.println(e);
                }
            }while (cursor2.moveToNext());
        }
        cursor2.close();
        Log.i("BookData:indexsize=", new Integer(j).toString());
        Log.i("BookIndex:indexsize=", new Integer(i).toString());
        Log.i("BookRead:indexsize=", new Integer(k).toString());
    }


}
