package com.lc.bangumidemo.Activity

import android.view.Menu
import android.view.Window
import com.lc.bangumidemo.R
import com.lc.bangumidemo.Util.FragmentUtil
import kotlinx.android.synthetic.main.mainlayout.*
import kotlinx.android.synthetic.main.novebook.*

class MainActivity :BaseActivity() {
    override fun setRes(): Int { return R.layout.mainlayout }

    override fun initview() {
        super.initview()
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
}