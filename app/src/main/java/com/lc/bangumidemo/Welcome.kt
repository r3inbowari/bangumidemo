package com.lc.bangumidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import com.lc.bangumidemo.Activity.MainActivity
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
        startActivity<MainActivity>()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.animate(welcome).scaleX(1.0f).scaleY(1.0f).setDuration(2000).setListener(this)
    }

}
