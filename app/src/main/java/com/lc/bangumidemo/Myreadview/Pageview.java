package com.lc.bangumidemo.Myreadview;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lc.bangumidemo.KT.KtactivityKt;

import java.util.ArrayList;
import java.util.List;

public class Pageview extends View {
    private String content;
    private Context context;
    public static int mytextsize= KtactivityKt.getFontsize();
    private float heigh=KtactivityKt.getHeight();
    private float width=KtactivityKt.getWidth();
    private List<String> txtlist=new ArrayList<String>();
    public Pageview(Context context) {
        super(context);
        this.context=context;
    }

    public Pageview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public Pageview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }
    public void setContent(String string)
    {
        content=string;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(content!=null)
        {
            Paint pen = new Paint();
            float fontsize = calcFontSize(mytextsize);
            pen.setTextSize(fontsize);
            float wid = fontsize * content.length();
            float widthsub = width - fontsize;
            int linecount = (int) (wid / widthsub) + 1;
            int linesize = (int) (widthsub / fontsize);
            int count = cuttxt(content, linecount, linesize);//返回加载多少个字能填满整个屏幕
            float h = pen.getTextSize();
            for (int i = 0; i < linecount; i++) {
                canvas.drawText(txtlist.get(i), fontsize / 2, (heigh - 16 * (h + 40)) / 2 + (heigh - 16 * (h + 40)) / 4 + i * (h + 40), pen);
            }
        }
    }
    public int cuttxt(String string,int linecount,int linesize)
    {
        txtlist.clear();
        int subsize= string.length();
        int addsize= (linesize*linecount-subsize);
        int temp = subsize+addsize;
        while (addsize!=0)
        {
            string=string+" ";
            addsize--;
        }
        int leng= string.length();
        for(int i=0;i<linecount;i++)
        {
            String t=string.substring(linesize*i,linesize*(i+1));
            txtlist.add(t);
        }
        return temp;
    }

    protected int calcFontSize(int size) {

        return (int)(size * context.getResources().getDisplayMetrics()

                .scaledDensity);

    }
}
