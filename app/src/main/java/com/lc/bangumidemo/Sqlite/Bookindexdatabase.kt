package com.lc.bangumidemo.Sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class Bookindexdatabase(
    private val mContext: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(mContext, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        //在数据库创建完成时创建Book表
        db.execSQL(Bookindexdatabase.CREATE_BOOK)
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        //将见表语句定义成字符串常量
        val CREATE_BOOK = ("create table Geegory ("
                + "id integer primary key autoincrement, "
                + "author text, "
                + "bookname text, "
                + "pagesize integer, "
                + "pageindex interger , "
                + "contentindex interger, "
                + "content text)"
                )

    }
}