package com.lc.bangumidemo.ReadView.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.lc.bangumidemo.R;


public class Menuview extends LinearLayout {
    public static LinearLayout linearLayout=null;
    MenuViewadapter menuViewadapter=null;
    public static Toolbar toolbar=null;
    public static FrameLayout frameLayout=null;
    public static Buttonview buttonmenu =null;
    public static Animation toorbaranimation=null;
    public static Animation toorbaranimationback=null;
    public static Animation buttonbaranimation=null;
    public static Animation buttonbaranimationback=null;
    public Menuview(Context context) {
        super(context);
        init(context);
    }

    public Menuview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Menuview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context)
    {
        LayoutInflater.from(context).inflate(R.layout.menuview,this,true);
        linearLayout=findViewById(R.id.readmenu);
        toolbar=findViewById(R.id.toolbar);
        frameLayout =findViewById(R.id.onclick);
        buttonmenu=findViewById(R.id.buttonmenu);

        toorbaranimation= AnimationUtils.loadAnimation(context,R.anim.toobaranim);
        toorbaranimationback= AnimationUtils.loadAnimation(context,R.anim.toobarbackanim);
        buttonbaranimation= AnimationUtils.loadAnimation(context,R.anim.buttonbar);
        buttonbaranimationback= AnimationUtils.loadAnimation(context,R.anim.buttonback);

    }
    public void setAdapt(MenuViewadapter Viewadapter)
    {
        this.menuViewadapter=Viewadapter;
    }
}
