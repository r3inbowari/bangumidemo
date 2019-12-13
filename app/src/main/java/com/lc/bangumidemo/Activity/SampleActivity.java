package com.lc.bangumidemo.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.app.Activity;

import android.os.Build;
import android.os.Handler;
import android.os.Message;

import android.view.GestureDetector;

import android.view.GestureDetector.OnGestureListener;

import android.view.Menu;

import android.view.MenuInflater;


import android.view.MotionEvent;

import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;

import android.view.animation.Animation;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.Toolbar;

import com.lc.bangumidemo.R;
import com.lc.bangumidemo.ReadView.LoadBitmapTask;
import com.lc.bangumidemo.ReadView.PageFlipView;
import com.lc.bangumidemo.ReadView.SinglePageRender;
import com.lc.bangumidemo.ReadView.View.Buttonview;
import com.lc.bangumidemo.ReadView.View.Menuleftview;
import com.lc.bangumidemo.ReadView.View.Menuview;
import com.lc.bangumidemo.Sqlite.Bookinsert;
import com.lc.bangumidemo.Sqlite.Bookselect;
import com.lc.bangumidemo.Sqlite.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;


/**

 * Sample Activity

 *

 * @author eschao

 */
public class SampleActivity extends Activity implements OnGestureListener {


    public String contxt =null;
    List<String> listpage = new ArrayList();
    PageFlipView mPageFlipView;
    MyDatabaseHelper dbhelper=null;
    GestureDetector mGestureDetector;
    Context context=null;
    SQLiteDatabase db=null;
    View.OnClickListener onClickListener=null;
    Menuview menuview=null;
    Menuleftview menuleftview=null;

    boolean islistleft =false;
    private ViewGroup contentView;
    ArrayAdapter<String> adapter=null;
    //加载动画
    public Animation leftshow=null;
    public Animation leftback=null;
    @Override
    protected void onStart() {
        super.onStart();
        //初始化数据库
//         dbhelper = new MyDatabaseHelper(this, "testt.db", null, 1);
//        Bookinsert.insert(dbhelper);
//        Bookselect.select(dbhelper);
        //
    }
    public static void loadtext(Context context)
    {
        Intent intent =new Intent(context,SampleActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageFlipView = new PageFlipView(this);
        setContentView(mPageFlipView);
        mGestureDetector = new GestureDetector(this, this);
        if (Build.VERSION.SDK_INT < 16) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            mPageFlipView.setSystemUiVisibility(
             View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
        //
//        onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.onclick:{
//                        toolbar.startAnimation(toorbaranimationback);
//                        buttonmenu.startAnimation(buttonbaranimationback);
//                    }
//                }
//            }
//        };

        //初始化菜单栏
//
//        contentView = findViewById(android.R.id.content);
//        menuview=new Menuview(SampleActivity.this);
//        menuleftview=new Menuleftview(SampleActivity.this);
//        if(Menuview.linearLayout!=null) {
//            linearLayout=Menuview.linearLayout;
//            toolbar=Menuview.toolbar;
//            frameLayout=Menuview.frameLayout;
//            buttonmenu=Menuview.buttonmenu;
//            toorbaranimation=Menuview.toorbaranimation;
//            toorbaranimationback=Menuview.toorbaranimationback;
//            buttonbaranimation=Menuview.buttonbaranimation;
//            buttonbaranimationback=Menuview.buttonbaranimationback;
//            inittopdata();
//            initanimlistener();
//            initlayoutlistener();
//        }
//        if(menuleftview!=null) {
//            linear=Menuleftview.linear;
//            tab=Menuleftview.tab;
//            listview=Menuleftview.listview;
//            buttonflesh=Menuleftview.buttonflesh;
//            buttonreadt=Menuleftview.buttonreadt;
//            leftshow= Menuleftview.leftshow;
//            leftback= Menuleftview.leftback;
//            initleftmenudata();
//            //添加监听
//            initanimbacklistener();
//        }
//        if(buttonmenu!=null)
//        {
//            bmulu=Buttonview.bmulu;
//            bhuanyuan=Buttonview.bhuanyuan;
//            bliangdu=Buttonview.bliangdu;
//            bset=Buttonview.bset;
//            bpre=Buttonview.bpre;
//            bnext=Buttonview.bnext;
//            seekBar=Buttonview.seekBar;
//            initbuttondata();
//            //添加监听
//            initButtoblistener();
//        }
    }
    //底部
    private void initbuttondata() { }
    //左边
    private void initleftmenudata() {
        //加载listdata
//        tab.setTitle( BookindexKt.getbook().getBookname());
//        adapter = new ArrayAdapter<String>(this,R.layout.listview2, BookindexKt.getbook().getHead());
//        listview.setAdapter(adapter);
    }
    //顶部
    private void inittopdata() {
        toolbar.setTitle("Hey~~~~~~~~");
    }

    private void initButtoblistener() {
        View.OnClickListener onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId())
                {
                    case R.id.mulu:
                    {
                        //genggaitupianyanse
                        contentView.removeView(menuview);
                        contentView.addView(menuleftview);
                        islistleft=true;
                    }
                }
            }
        };
        bmulu.setOnClickListener(onClickListener);
        bhuanyuan.setOnClickListener(onClickListener);
        bliangdu.setOnClickListener(onClickListener);
        bset.setOnClickListener(onClickListener);
        bpre.setOnClickListener(onClickListener);
        bnext.setOnClickListener(onClickListener);

    }

    private void initanimbacklistener() {
        Animation.AnimationListener animationL=new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                contentView.removeView(menuleftview);
                islistleft=false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        leftback.setAnimationListener(animationL);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menumain,menu);

        return super.onCreateOptionsMenu(menu);
    }



    private void initanimlistener() {
        Animation.AnimationListener animationListener =new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                contentView.removeView(menuview);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        toorbaranimationback.setAnimationListener(animationListener);
    }

    private void initlayoutlistener() {
        frameLayout.setOnClickListener(onClickListener);
    }
    //初始化顶部
    public LinearLayout linearLayout=null;
    public Toolbar toolbar=null;
    public FrameLayout frameLayout=null;
    public Animation toorbaranimation=null;
    public Animation toorbaranimationback=null;
    public Animation buttonbaranimation=null;
    public Animation buttonbaranimationback=null;
    public Buttonview buttonmenu =null;
    //初始化左边
    public LinearLayout linear=null;
    public Toolbar tab=null;
    public ListView listview=null;
    public Button buttonflesh=null;
    public Button buttonreadt=null;
    //初始化底部菜单
    public Button bmulu=null;
    public Button bhuanyuan=null;
    public Button bliangdu=null;
    public Button bset=null;
    public Button bpre=null;
    public Button bnext=null;
    public SeekBar seekBar=null;
    @Override

    protected void onResume() {

        super.onResume();



        LoadBitmapTask.get(this).start();

        mPageFlipView.onResume();

    }

    @Override

    protected void onPause() {

        super.onPause();



        mPageFlipView.onPause();

        LoadBitmapTask.get(this).stop();

    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {

            mPageFlipView.onFingerUp(event.getX(), event.getY());


        }


        return mGestureDetector.onTouchEvent(event);

    }



    @Override

    public boolean onDown(MotionEvent e) {

        mPageFlipView.onFingerDown(e.getX(), e.getY());

        return true;

    }
    public void dealstring(String st ,int size)
    {
        String sting=st;
        int pagec=st.length()/size+1;
        int sumsize = pagec*size;
        int add=sumsize-st.length();
        while (add!=0)
        {
            sting += " ";
            add--;
        }
        for (int i=0;i<pagec;i++) {
            String ste=sting.substring(i*size,(i+1)*size);
            listpage.add(ste);
        }
    }
//
//    public  void getcontext(final String  href)
//    {
//        final Handler mHamdler1 = new Handler(){
//
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//                switch (msg.what)
//                {
//                    case 2 :
//
//                        contxt=msg.obj.toString();
//                        int size = 0;
//                        dealstring(contxt,size);
//                        PageUtil.start(SampleActivity.this,listpage);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
////                Soup soup1 =new Soup();
////                soup1.getcontext(href,mHamdler1,contxt);
//
//            }
//        }).start();
//
//
//
//    }

    final int distance =80;
    GestureDetector gestureDetector = null;
    @Override

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX()-e2.getX()>distance) {
            Toast.makeText(SampleActivity.this, "left", Toast.LENGTH_SHORT).show();
//            if (SinglePageRender.num == LoadBitmapTask.getpagetxtsize()) {
//                PageUtil.clean();
//                int index = BookindexKt.getIndex(BookindexKt.getbook());
//                BookindexKt.setIndex(BookindexKt.getbook(), ++index);
//                Book book = BookindexKt.getBook();
//                int indextest = BookindexKt.getIndex(BookindexKt.getBook());
//                ContentValues values = new ContentValues();
//                values.put("bookindex", indextest);
//                db.update("Book", values, "name = ?", new String[]{book.getBookname()});
//                values.clear();
//                db.close();
//               getcontext(BookindexKt.getbook().getHref().get(index));

//            }
        }
        else if(e2.getX()-e1.getX()>distance)
        {

            Toast.makeText(SampleActivity.this, "right", Toast.LENGTH_SHORT).show();
 //           if (SinglePageRender.num == 1) {
//                PageUtil.clean();
//                int index = BookindexKt.getIndex(BookindexKt.getbook());
//                BookindexKt.setIndex(BookindexKt.getbook(), --index);
//                Book book = BookindexKt.getBook();
//                int indextest = BookindexKt.getIndex(BookindexKt.getBook());
//                ContentValues values = new ContentValues();
//                values.put("bookindex", indextest);
//                db.update("Book", values, "name = ?", new String[]{book.getBookname()});
//                values.clear();
//                db.close();
//                getcontext(BookindexKt.getbook().getHref().get(index));
//
//            }
        }
        return false;

    }
    @Override

    public void onLongPress(MotionEvent e) {

    }



    @Override

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,

                            float distanceY) {

        mPageFlipView.onFingerMove(e2.getX(), e2.getY());

        return true;

    }



    @Override

    public void onShowPress(MotionEvent e) {

    }



    public boolean onSingleTapUp(MotionEvent e) {
        if(islistleft==false) {
            Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
            contentView.addView(menuview);
            buttonmenu.startAnimation(buttonbaranimation);
            toolbar.startAnimation(toorbaranimation);
        }
        else
        {
            //关闭list、
            menuleftview.startAnimation(leftback);
            islistleft=false;
        }

        return false;

    }





}