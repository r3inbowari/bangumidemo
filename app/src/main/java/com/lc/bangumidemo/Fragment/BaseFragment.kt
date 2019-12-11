package com.lc.bangumidemo.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view =inflater.inflate(setRes(),container,false)
        return view
    }
    /**
    *初始化初始布局
    */
    abstract fun setRes():Int

    override fun onStart() {
        super.onStart()
        startaction()
    }

    open fun startaction(){}

}