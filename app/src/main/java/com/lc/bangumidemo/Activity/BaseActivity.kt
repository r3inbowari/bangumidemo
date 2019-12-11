package com.lc.bangumidemo.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setRes())
        initview()
        initlistener()
    }



    override fun onStart() {
        super.onStart()
        startaction()
    }

    /**
     * 初始化初始布局
     */
    abstract fun setRes():Int

    /**
     * 初始化视图
     */
    open fun initview() {}
    /**
     * 初始化监听
     */
    open fun initlistener() {}

    /**
     * 编写你的行为
     */
    open fun startaction(){}



}