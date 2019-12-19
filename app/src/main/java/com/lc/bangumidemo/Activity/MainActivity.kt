package com.lc.bangumidemo.Activity

import android.view.Menu

import com.lc.bangumidemo.R
import com.lc.bangumidemo.Util.FragmentUtil
import kotlinx.android.synthetic.main.mainlayout.*


class MainActivity :BaseActivity() {
    override fun setRes(): Int { return R.layout.mainlayout }

    override fun initview() {
        super.initview()
    }

    override fun initaction() {
        super.initaction()

    }

    override fun initlistener() {
        super.initlistener()
        //bottombar监听用于切换fragment
        bottomBar.setOnTabSelectListener {

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it)!!,it.toString())
            transaction.commit()


        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menumain,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onRestart() {
        super.onRestart()
    }
}