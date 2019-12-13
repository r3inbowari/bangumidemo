package com.lc.bangumidemo.KT

import android.content.Context
import android.graphics.Bitmap
import com.lc.bangumidemo.Activity.SampleActivity
import com.lc.bangumidemo.MyRetrofit.ResClass.BookDetail
import com.lc.bangumidemo.MyRetrofit.ResClass.Bookdata
import com.lc.bangumidemo.R
import com.lc.bangumidemo.ReadView.DoublePagesRender
import com.lc.bangumidemo.ReadView.LoadBitmapTask
import com.lc.bangumidemo.Sqlite.MyDatabaseHelper
import com.lc.bangumidemo.Sqlite.Nvdetil
import com.lc.bangumidemo.Sqlite.SqlUtil
import java.util.ArrayList

var bookDetail : BookDetail?=null
var imglist : MutableList<Bitmap> = ArrayList<Bitmap>()
var list: MutableList<Bookdata> = mutableListOf()
var pagetxt: MutableList<String> = ArrayList() //pagecontentlist
var width=0      //初始屏幕宽度
var height=0     //初始屏幕高度
var fontsize =23 //默认字体大小
var linesize =16 //默认显示行数


/**
 * 获取一页字数（字体大小，显示行数）
 */
object PagesizeUtil{

    fun getpagesize(context: Context, fontsize: Int, lintcount: Int): Int {

        return getcount(context, width, height, fontsize, lintcount)
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


object PageUtil {
    //设置字体大小
    fun setFontsize(size: Int) {
        //SinglePageRender.mytextsize=size;
        fontsize = size
    }

    init {
        LoadBitmapTask.addpicture(R.drawable.background)
        LoadBitmapTask.addpicture(R.drawable.background)
        LoadBitmapTask.addpicture(R.drawable.background)
    }

    //设置背景
    fun setbackground(ID: Int) {
        //pagelist.clear();
        LoadBitmapTask.addpicture(ID)
        LoadBitmapTask.addpicture(ID)
        LoadBitmapTask.addpicture(ID)
    }
    //装载并获取已装载页数
    fun getpagecount(context: Context): Int {
        var record=
            Nvdetil(null,bookDetail?.data?.name, bookDetail?.data?.author,null,null,null,null)
        var dbhelper = MyDatabaseHelper(context, "bookindexssss.db", null, 1)
        val res:Nvdetil=SqlUtil.findbookisexist(dbhelper,record)
        res.pagesize?.let { DoublePagesRender.setpicturesize(it) }
        return res.pagesize!!
    }
    //装载一页数据
    fun loadtxt(txt: String) {
        pagetxt.add(txt)//加载一页数据
    }

    //装载并获取已装载页数
    fun startact(context: Context, st: List<String>) {
        for (s in st) {
            loadtxt(s)
        }
        getpagecount(context)
        SampleActivity.loadtext(context)
    }

    fun clean() {
        pagetxt.clear()
    }


}

