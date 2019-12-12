package com.lc.bangumidemo.ReadView.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.lc.bangumidemo.R;


public class Buttonview extends LinearLayout {
    public static Button bmulu=null;
    public static Button bhuanyuan=null;
    public static Button bliangdu=null;
    public static Button bset=null;
    public static Button bpre=null;
    public static Button bnext=null;
    public static SeekBar seekBar=null;



    public Buttonview(Context context) {
        super(context);
        init(context);
    }

    public Buttonview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Buttonview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context)
    {
        LayoutInflater.from(context).inflate(R.layout.buttonbar,this,true);
        bmulu=findViewById(R.id.mulu);
        bhuanyuan=findViewById(R.id.huanyuan);
        bliangdu=findViewById(R.id.liangdu);
        bset=findViewById(R.id.shezhi);
        bpre=findViewById(R.id.pre);
        bnext=findViewById(R.id.next);
        seekBar=findViewById(R.id.seekbar);

    }
}
