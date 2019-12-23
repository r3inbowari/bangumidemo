package com.lc.bangumidemo.KT

import android.content.Context
import android.graphics.Bitmap
import com.lc.bangumidemo.MyRetrofit.ResClass.BookDetail
import com.lc.bangumidemo.MyRetrofit.ResClass.Bookdata
import com.lc.bangumidemo.Sqlite.*

import java.util.ArrayList


var bookDetail : BookDetail?=null
var imglist : MutableList<Bitmap> = ArrayList<Bitmap>()
var list: MutableList<Bookdata> = mutableListOf()
var screenwidth=0      //初始屏幕宽度
var screenheight=0    //初始屏幕高度
var fontsize =23 //默认字体大小
var linesize =16 //默认显示行数
var position:Int=0

var hardpageindex=0
var hardcontentindex=0



/**
 * 获取一页字数（字体大小，显示行数）
 */
object PagesizeUtil{

    fun getpagesize(context: Context, fontsize: Int, lintcount: Int): Int {

        return getcount(context, screenwidth, screenheight, fontsize, lintcount)
    }
    fun getcount(context: Context, width: Int, height: Int, fontsize: Int, lincount: Int): Int {

        val st = "吖"
        val si = cal(fontsize, context)
        val w1 = si * st.length
        val widthsub = width - si
        val linecount = (w1 / widthsub).toInt() + 1
        val linesize = (widthsub / si).toInt()
        val count = cuttxtt(st, linecount, linesize)//返回加载多少个字能填满一行
        return lincount * count
    }
    fun cal(size: Int, context: Context): Float {
        return (size * context.resources.displayMetrics

            .scaledDensity).toInt().toFloat()
    }
    fun cuttxtt(string: String, linecount: Int, linesize: Int): Int {
        val subsize = string.length
        val addsize = linesize * linecount - subsize

        return subsize + addsize
    }

    fun txttolist(st :String,context: Context, fontsize: Int, lintcount: Int): MutableList<String> {
        var listpage: MutableList<String> = ArrayList()
        var sting=st
        var apagesize=getpagesize(context,fontsize,lintcount)
        var pagec=st.length/apagesize+1
        var sumsize = pagec*apagesize
        var add=sumsize-st.length
        while (add!=0)
        {
            sting += " "
            add--
        }
        for (i in 0 until pagec) {
            var ste=sting.substring(i*apagesize,(i+1)*apagesize)
            listpage.add(ste)
        }
        return listpage
    }
}
fun destoryandsave(context: Context)
{
    Bookreadclean.clean(context)
    //查询是否存在索引
    var db= MyDatabaseHelper(context,"bookstore",null,1)
    var selectindex = Selectclass(bookDetail!!.data.name, bookDetail!!.data.author, bookDetail!!.list.size)
    var returnsult= Bookselect.selectindex(db,selectindex)
    if (returnsult != null) {
        hardpageindex=returnsult.pageindex
    }
    if (returnsult != null) {
        hardcontentindex=returnsult.contentindex
    }
    var destoryvalue= BookIndexclass(null, bookDetail!!.data.author, bookDetail!!.data.name,
        hardpageindex, hardcontentindex, bookDetail!!.list.size, hardpageindex, hardcontentindex)
    Bookupdata.updata(db,destoryvalue)
}



