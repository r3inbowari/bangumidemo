package com.lc.bangumidemo.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Bookreadclean {
    public static void clean(Context context){
        MyDatabaseHelper dbhelper=new MyDatabaseHelper(context,"bookstore",null,1);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("BookRead",null,null);
        db.close();

    }
}
