package com.lc.bangumidemo.ReadView.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.lc.bangumidemo.R;


public class Menuleftview extends LinearLayout {
    public static LinearLayout linear=null;
    public static Toolbar tab=null;
    public static ListView listview=null;
    public static Button buttonflesh=null;
    public static Button buttonreadt=null;
    public static Animation leftshow=null;
    public static Animation leftback=null;
    public Menuleftview(Context context) {
        super(context);
        init(context);
    }

    public Menuleftview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Menuleftview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context)
    {
        LayoutInflater.from(context).inflate(R.layout.menureadleft,this,true);
        linear=findViewById(R.id.liesview);
        tab=findViewById(R.id.tab);
        listview=findViewById(R.id.list);
        buttonflesh=findViewById(R.id.fleshbutton);
        buttonreadt=findViewById(R.id.dore);
        leftshow= AnimationUtils.loadAnimation(context,R.anim.leftshow);
        leftback= AnimationUtils.loadAnimation(context,R.anim.leftclose);
    }
}
