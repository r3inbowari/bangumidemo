package com.lc.bangumidemo.Sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class Bookupdata {
    public static void updata(MyDatabaseHelper dbhelper, Nvdetil data){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //开始组装第一条数据
        values.put("author", data.getAuthor());
        values.put("bookname", data.getBookname());
        values.put("pagesize",data.getPagesize());
        values.put("pageindex", data.getPageindex());
        values.put("contentindex", data.getContentindex());
        values.put("content", data.getContent());
        db.update("Category",values,"bookname= ? And author= ? ",new String[]{data.getBookname(),data.getAuthor()});//更新数据
        values.clear();//！！！！！
    }
}
