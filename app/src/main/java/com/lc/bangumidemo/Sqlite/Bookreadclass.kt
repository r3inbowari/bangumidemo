package com.lc.bangumidemo.Sqlite

/**
 *         val CREATE_BOOKREAD = ("create table BookRead ("
+ "id integer primary key autoincrement, "
+ "author text, "
+ "bookname text, "
+ "pagecount integer, "
+ "data text, "
+ "pageindex interger, "
+ "contentindex interger, "
+ "start interger, "
+ "end interger, "
+ "index interger)"
)
 */
data class Bookreadclass(
    val author:String,
    val bookname:String,
    val pagecount:Int,
    val bookdata:String,
    val pageindex:Int,
    val contentindex:Int,
    val start:Int,
    val end:Int,
    val indexx:Int
)