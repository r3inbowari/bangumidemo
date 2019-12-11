package com.lc.bangumidemo.Activity

import android.view.Window
import com.lc.bangumidemo.R
import kotlinx.android.synthetic.main.mainlayout.*

class MainActivity :BaseActivity() {
    override fun setRes(): Int { return R.layout.mainlayout }

    override fun initview() {
        super.initview()
        //全屏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setSupportActionBar(toolbar)
    }

    override fun initlistener() {
        super.initlistener()
        //bottombar监听
        bottomBar.setOnTabSelectListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it)!!,it.toString())
            transaction.commit()
        }

    }
}