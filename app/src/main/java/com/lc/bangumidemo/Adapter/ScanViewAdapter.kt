package com.lc.bangumidemo.Adapter


import android.content.Context
import android.content.res.AssetManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast

import com.lc.bangumidemo.KT.*
import com.lc.bangumidemo.Myreadview.Pageview
import com.lc.bangumidemo.Myreadview.ScanView
import com.lc.bangumidemo.R
import com.lc.bangumidemo.Sqlite.BookIndexclass
import com.lc.bangumidemo.Sqlite.BookReadclass
import com.lc.bangumidemo.Sqlite.Bookselect
import com.lc.bangumidemo.Sqlite.Bookupdata
import com.lc.bangumidemo.Sqlite.MyDatabaseHelper
import com.lc.bangumidemo.Sqlite.Selectclass

class ScanViewAdapter(
    internal var context: Context,
    startindex: BookIndexclass
) : PageAdapter() {
    internal var am: AssetManager
    internal var db: MyDatabaseHelper? = null
    internal var selectclass: Selectclass? = null
    internal var startindex: BookIndexclass? = null

    override val count: Int
        get() = 0

    override val view: View
        get() = LayoutInflater.from(context).inflate(
            R.layout.page_layout,
            null
        )

    init {
        am = context.assets
        db=MyDatabaseHelper(context,"bookstore",null,1)
        selectclass = Selectclass(startindex.bookname, startindex.author, startindex.pagecount)
        this.startindex = startindex
    }
        fun setonPageclickListener(view: ScanView,onpageclick: ScanView.OnpageClick){
            view.setOnpageClick(onpageclick)
        }
    override fun addContent(view: View, position: Int) {
        ScanView.stopleft=false
        ScanView.stopright=false
        var retsult: BookReadclass? = null
        val pageview = view.findViewById<Pageview>(R.id.pageview)
        var st: String?
        Bookselect.selectalldata(db!!)
        try {
            retsult = selectclass?.let { Bookselect.selectbookreaddata(db!!, it, position) }
            st = retsult!!.bookdata
            pageview.setContent(st)
        } catch (e: Exception) { }

            try {
            if (ScanView.INDEXTAGRIG == true || ScanView.INDEXTAGLETE == true) {
                var requese: BookIndexclass?
                if (ScanView.INDEXTAGRIG == true) {
                    requese = BookIndexclass(null, startindex!!.author, startindex!!.bookname, startindex!!.hardpageindex, startindex!!.hardcontentindex, startindex!!.pagecount, retsult!!.pageindex, retsult.contentindex - 1)
                    Bookupdata.updata(db!!, requese)
                    Mapupdata(context, requese)
                } else {
                    requese = BookIndexclass(null, startindex!!.author, startindex!!.bookname, startindex!!.hardpageindex, startindex!!.hardcontentindex, startindex!!.pagecount, retsult!!.pageindex, retsult.contentindex + 1)
                    Bookupdata.updata(db!!, requese)
                    Mapupdata(context, requese)
                }
                ScanView.INDEXTAGRIG = false
                ScanView.INDEXTAGLETE = false
            }}catch (e:KotlinNullPointerException){
                //Toast.makeText(context,"已经没有内容了~~~",Toast.LENGTH_LONG).show()
                if(ScanView.INDEXTAGRIG == true) {
                    Toast.makeText(context, "已经没有内容了~~~", Toast.LENGTH_LONG).show()

                    ScanView.stopright=true
                }
                if(ScanView.INDEXTAGLETE == true){
                    Toast.makeText(context, "已经没有内容了~~~", Toast.LENGTH_LONG).show()
                    ScanView.stopleft=true
                }
            }
    }
}