package com.lc.bangumidemo.Activity

import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.lc.bangumidemo.KT.height
import com.lc.bangumidemo.KT.imglist
import com.lc.bangumidemo.KT.width
import com.lc.bangumidemo.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class Welcome : AppCompatActivity() ,ViewPropertyAnimatorListener{
    override fun onAnimationCancel(view: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAnimationStart(view: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAnimationEnd(view: View?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
       //获取屏幕数据
        var display = getWindowManager().getDefaultDisplay();
        width=display.width
        height=display.height
        startActivity<MainActivity>()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.animate(welcome).scaleX(1.0f).scaleY(1.0f).setDuration(2000).setListener(this)
    }

}
