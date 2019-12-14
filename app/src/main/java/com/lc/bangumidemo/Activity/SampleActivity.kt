package com.lc.bangumidemo.Activity

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle

import android.app.Activity

import android.os.Build
import android.os.Handler
import android.os.Message

import android.view.GestureDetector

import android.view.GestureDetector.OnGestureListener

import android.view.Menu

import android.view.MenuInflater


import android.view.MotionEvent

import android.view.View

import android.view.ViewGroup
import android.view.WindowManager

import android.view.animation.Animation

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ListView

import android.widget.SeekBar
import android.widget.Toast

import androidx.appcompat.widget.Toolbar

import com.lc.bangumidemo.KT.*
import com.lc.bangumidemo.KT.PageUtil
import com.lc.bangumidemo.KT.PagesizeUtil
import com.lc.bangumidemo.MyRetrofit.ResClass.BookContent
import com.lc.bangumidemo.MyRetrofit.ResClass.BookDetail
import com.lc.bangumidemo.MyRetrofit.Retrofit.Retrofitcall
import com.lc.bangumidemo.R
import com.lc.bangumidemo.ReadView.DoublePagesRender
import com.lc.bangumidemo.ReadView.LoadBitmapTask
import com.lc.bangumidemo.ReadView.PageFlipView
import com.lc.bangumidemo.ReadView.PageRender
import com.lc.bangumidemo.ReadView.SinglePageRender
import com.lc.bangumidemo.ReadView.View.Buttonview
import com.lc.bangumidemo.ReadView.View.Menuleftview
import com.lc.bangumidemo.ReadView.View.Menuview
import com.lc.bangumidemo.Sqlite.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.ArrayList


/**
 *
 * Sample Activity
 *
 *
 *
 * @author eschao
 */
class SampleActivity : Activity(), OnGestureListener {


    var contxt: String? = null
    internal var listpage: MutableList<String> = ArrayList()
    internal lateinit var mPageFlipView: PageFlipView
    internal lateinit var mGestureDetector: GestureDetector
    internal var context: Context? = null
    internal var db: SQLiteDatabase? = null
    internal val distance = 80
    override fun onStart() {
        super.onStart()
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPageFlipView = PageFlipView(this)
        setContentView(mPageFlipView)
        isact=true
        mGestureDetector = GestureDetector(this, this)
        if (Build.VERSION.SDK_INT < 16) {

            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        } else {
            mPageFlipView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }
  override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menumain, menu)

        return super.onCreateOptionsMenu(menu)
    }




    override fun onResume() {

        super.onResume()



        LoadBitmapTask.get(this).start()

        mPageFlipView.onResume()

    }

    override fun onPause() {

        super.onPause()



        mPageFlipView.onPause()

        LoadBitmapTask.get(this).stop()

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {

            mPageFlipView.onFingerUp(event.x, event.y)


        }


        return mGestureDetector.onTouchEvent(event)

    }


    override fun onDown(e: MotionEvent): Boolean {

        mPageFlipView.onFingerDown(e.x, e.y)

        return true

    }

    fun dealstring(st: String, size: Int) {
        var sting = st
        val pagec = st.length / size + 1
        val sumsize = pagec * size
        var add = sumsize - st.length
        while (add != 0) {
            sting += " "
            add--
        }
        for (i in 0 until pagec) {
            val ste = sting.substring(i * size, (i + 1) * size)
            listpage.add(ste)
        }
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        if (e1.x - e2.x > distance) {
            if((SinglePageRender.num==(pagetxt.size-1))&& isact) {
                isact=false
                loadbookdatatopage(this, bookDetail, position + 1)
            }
        } else if (e2.x - e1.x > distance) {
            if((SinglePageRender.num==0)&& isact) {
                isact=false
                loadbookdatatopage(this, bookDetail, position - 1)
            }
        }
        return false

    }


    override fun onLongPress(e: MotionEvent) {

    }


    override fun onScroll(
        e1: MotionEvent, e2: MotionEvent, distanceX: Float,

        distanceY: Float
    ): Boolean {

        mPageFlipView.onFingerMove(e2.x, e2.y)

        return true

    }


    override fun onShowPress(e: MotionEvent) {

    }


    override fun onSingleTapUp(e: MotionEvent): Boolean {

        return false

    }

    companion object {
        fun loadtext(context: Context) {
            val intent = Intent(
                context,
                SampleActivity::class.java
            ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }



}