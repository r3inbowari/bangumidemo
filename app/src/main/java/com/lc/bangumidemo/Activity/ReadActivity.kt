package com.lc.bangumidemo.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MenuItem

import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle

import com.lc.bangumidemo.Adapter.ScanViewAdapter
import com.lc.bangumidemo.KT.*
import com.lc.bangumidemo.Myreadview.ScanView
import com.lc.bangumidemo.R


import com.lc.bangumidemo.RxBus.RxBus
import com.lc.bangumidemo.RxBus.RxBusBaseMessage


import com.lc.bangumidemo.Sqlite.*
import com.trello.rxlifecycle3.android.lifecycle.kotlin.bindUntilEvent

import io.reactivex.disposables.Disposable

import kotlinx.android.synthetic.main.tesst.*

class ReadActivity :BaseActivity() {
    var ismenushow=false
    var islistshow=false
    lateinit var disposable: Disposable
    override fun setRes(): Int {
        return R.layout.tesst
    }

    override fun initview() {
        super.initview()
        readtoolbar.isVisible=false
        buttonmenu.isVisible=false
        leftmenu.isVisible=false
        avi.show()
    }

    fun Rxrecive(code:Int){
        RxBus.getInstance()
            .tObservable(code, RxBusBaseMessage::class.java)
            .bindUntilEvent(this,Lifecycle.Event.ON_DESTROY)
            .subscribe(object : io.reactivex.functions.Consumer<RxBusBaseMessage> {

                override fun accept(t: RxBusBaseMessage?) {
                    if(t!!.code==0){
                        Log.e("RXJAVA","初始化初始章节")
                        var indexres=    Bookselect.selectbookindex(this@ReadActivity)!!
                        Mapinit(this@ReadActivity,indexres)
                    }
                    if(t!!.code==1){
                        Log.e("RXJAVA","开始初始化前后章")
                        var indexres=    Bookselect.selectbookindex(this@ReadActivity)!!
                        InitMapupdata(this@ReadActivity,indexres)
                    }
                    if(t!!.code==2){
                        Log.e("RXJAVA","初始化完成进入视图")
                        initmyview()
                        avi.hide()
                    }
                }

            })
    }

    override fun initaction() {
        super.initaction()
        Rxrecive(0)//注册初始化订阅者
        Rxrecive(1)//注册初始化订阅者
        Rxrecive(2)//注册初始化订阅者
        //查询索引信息
        Bookselect.selectbookindex(this)
        //开始进行加载
        initloadbookdatatopage(this, bookDetail, hardpageindex)

        }

    fun initmyview() {


        var returnsult= Bookselect.selectbookindex(this)
        var adapt= returnsult?.let { ScanViewAdapter(this, it) }
        adapt?.let { pageview.setAdapter(it) }
        adapt?.setonPageclickListener(pageview,object :ScanView.OnpageClick{
            override fun onItemClick() {
                when(ismenushow){
                    true->{
                        readtoolbar.isVisible=false
                        buttonmenu.isVisible=false
                        ismenushow=false
                    }
                    false->{
                        readtoolbar.isVisible=true
                        buttonmenu.isVisible=true
                        ismenushow=true
                    }
                }
                when(islistshow){
                    true->{
                        leftmenu.isVisible=false
                        islistshow=false
                    }
                }

            }
        })

        //初始化阅读界面菜单
        setSupportActionBar(readtoolbar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)


    }

    override fun initlistener() {
        super.initlistener()
        mulu.setOnClickListener {
            readtoolbar.isVisible=false
            buttonmenu.isVisible=false
            ismenushow=false

            //open list
            leftmenu.isVisible=true
            islistshow=true
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        destoryandsave(this)
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
                android.R.id.home ->  {
                    destoryandsave(this)
                    finish()
                }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        //查询索引信息
        Bookselect.selectbookindex(this)
        //开始进行加载
        initloadbookdatatopage(this, bookDetail, hardpageindex)
    }
}