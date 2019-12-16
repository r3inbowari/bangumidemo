package com.lc.bangumidemo.Sqlite

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
data class Bookindexclass(
    val id: Int?,
    val author: String,
    val bookname: String,
    val hardpageindex: Int,
    val hardcontentindex: Int,
    val pagecount: Int,
    val pageindex: Int,
    val contentindex: Int
)