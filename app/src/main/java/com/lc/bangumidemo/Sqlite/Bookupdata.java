package com.lc.bangumidemo.Sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
/**
 *     val CREATE_BOOKINDEX = ("create table BookIndex ("
 + "id integer primary key autoincrement, "
 + "author text, "
 + "bookname text, "
 + "hardpageindex interger, "
 + "hardcontentindex interger, "
 + "pagecount integer, "
 + "pageindex interger, "
 + "contentindex interger)"
 )
 */
public class Bookupdata {
    public static void updata(MyDatabaseHelper dbhelper, Bookindexclass data){
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
        db.update("Bookindex",values,"bookname= ? And author= ? ",new String[]{data.getBookname(),data.getAuthor()});//更新数据
        values.clear();//！！！！！
        Bookselect.selectalldata(dbhelper);
    }
}
