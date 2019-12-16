package com.lc.bangumidemo.Activity

import android.view.GestureDetector
import android.view.MotionEvent
import com.lc.bangumidemo.KT.bookDetail
import com.lc.bangumidemo.KT.hardcontentindex
import com.lc.bangumidemo.KT.hardpageindex
import com.lc.bangumidemo.Myreadview.ScanViewAdapter


import com.lc.bangumidemo.R
import com.lc.bangumidemo.Sqlite.*
import kotlinx.android.synthetic.main.tesst.*


class testclass :BaseActivity(), GestureDetector.OnGestureListener {
    internal lateinit var mGestureDetector: GestureDetector

    override fun setRes(): Int {
        return R.layout.tesst
    }

    override fun initaction() {
        super.initaction()



    }

    override fun initview() {
        super.initview()
        mGestureDetector = GestureDetector(this, this)
        var db=MyDatabaseHelper(this,"bookstore",null,1)
        var selectindex = Selectclass(bookDetail!!.data.name, bookDetail!!.data.author, bookDetail!!.list.size)
        var returnsult= Bookselect.selectindex(db,selectindex)
        var adapt=ScanViewAdapter(this,db,returnsult)
        pageview.setAdapter(adapt)
    }

    override fun startaction() {
        super.startaction()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

           return mGestureDetector.onTouchEvent(event)
    }

    override fun onShowPress(e: MotionEvent?) {
    //    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
  //      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    return false
    }

    override fun onDown(e: MotionEvent?): Boolean
    {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    return false
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    return false
    }

    override fun onLongPress(e: MotionEvent?) {
  //      TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


    }

    override fun onDestroy() {
        super.onDestroy()
        Bookreadclean.clean(this)
        //查询是否存在索引
        var db=MyDatabaseHelper(this,"bookstore",null,1)
        var selectindex = Selectclass(bookDetail!!.data.name, bookDetail!!.data.author, bookDetail!!.list.size)
        var returnsult=Bookselect.selectindex(db,selectindex)
        hardpageindex=returnsult.pageindex
        hardcontentindex=returnsult.contentindex
        var destoryvalue=Bookindexclass(null, bookDetail!!.data.author, bookDetail!!.data.name,
            hardpageindex, hardcontentindex, bookDetail!!.list.size, hardpageindex, hardcontentindex)
        Bookupdata.updata(db,destoryvalue)

    }
}