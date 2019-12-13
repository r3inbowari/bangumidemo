package com.lc.bangumidemo.ReadView;
import android.content.Context;

import android.content.SharedPreferences;

import android.opengl.GLSurfaceView;

import android.opengl.GLSurfaceView.Renderer;

import android.os.Handler;

import android.os.Message;

import android.preference.PreferenceManager;

import android.util.AttributeSet;

import android.util.Log;



import com.eschao.android.widget.pageflip.PageFlip;

import com.eschao.android.widget.pageflip.PageFlipException;
import com.lc.bangumidemo.KT.KtactivityKt;
import com.lc.bangumidemo.Sqlite.Bookindexdatabase;
import com.lc.bangumidemo.Sqlite.MyDatabaseHelper;
import com.lc.bangumidemo.Sqlite.Nvdetil;
import com.lc.bangumidemo.Sqlite.SqlUtil;


import java.util.concurrent.locks.ReentrantLock;



import javax.microedition.khronos.egl.EGLConfig;

import javax.microedition.khronos.opengles.GL10;



/**

 * Page flip view

 *

 * @author eschao

 */



public class PageFlipView extends GLSurfaceView implements Renderer {



    private final static String TAG = "PageFlipView";



    public static int mPageNo;

    int mDuration;

    Handler mHandler;

    PageFlip mPageFlip;

    PageRender mPageRender;

    ReentrantLock mDrawLock;
    Context context= null;


    public PageFlipView(Context context) {

        super(context);
        this.context=context;
        init(context);

    }



    public PageFlipView(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context=context;
        init(context);

    }



    private void init(Context context) {

        // create handler to tackle message

        newHandler();



        // load preferences

        SharedPreferences pref = PreferenceManager

                .getDefaultSharedPreferences(context);

        mDuration = pref.getInt(Constants.PREF_DURATION, 5000);

        int pixelsOfMesh = pref.getInt(Constants.PREF_MESH_PIXELS, 10);

        boolean isAuto = pref.getBoolean(Constants.PREF_PAGE_MODE, true);



        // create PageFlip

        mPageFlip = new PageFlip(context);

        mPageFlip.setSemiPerimeterRatio(0.8f)

                .setShadowWidthOfFoldEdges(5, 60, 0.3f)

                .setShadowWidthOfFoldBase(5, 80, 0.4f)

                .setPixelsOfMesh(pixelsOfMesh)

                .enableAutoPage(isAuto);

        setEGLContextClientVersion(2);



        // init others
        //每次启动前查询是否有记录 同时记录当前位置
        MyDatabaseHelper dbhelper =new  MyDatabaseHelper(context, "bookindexssss.db", null, 1);
        Bookindexdatabase datahelp = new Bookindexdatabase(context, "bookdatassss.db", null, 1);
        String name= KtactivityKt.getBookDetail().getData().getName();
        String author =KtactivityKt.getBookDetail().getData().getAuthor();
        int pagesize =KtactivityKt.getBookDetail().getList().size();
        //获取游标
        Nvdetil r =new Nvdetil(null, name,author,pagesize,null,null,null);
        Nvdetil retu = SqlUtil.findbookisexist(dbhelper,r);
        int pageindex=retu.getPageindex();
        int contentindex=retu.getContentindex();

        mPageNo = contentindex;
        mDrawLock = new ReentrantLock();

        mPageRender = new SinglePageRender(context, mPageFlip,

                mHandler, mPageNo);

        // configure render

        setRenderer(this);

        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

    }




    /**

     * Is auto page mode enabled?

     *

     * @return true if auto page mode enabled

     */

    public boolean isAutoPageEnabled() {

        return mPageFlip.isAutoPageEnabled();

    }




    /**

     * Get duration of animating

     *

     * @return duration of animating

     */

    public int getAnimateDuration() {

        return mDuration;

    }



    /**

     * Set animate duration

     *

     * @param duration duration of animating

     */

    public void setAnimateDuration(int duration) {

        mDuration = duration;

    }



    /**

     * Get pixels of mesh

     *

     * @return pixels of mesh

     */

    public int getPixelsOfMesh() {

        return mPageFlip.getPixelsOfMesh();

    }



    /**

     * Handle finger down event

     *

     * @param x finger x coordinate

     * @param y finger y coordinate

     */

    public void onFingerDown(float x, float y) {

        // if the animation is going, we should ignore this event to avoid

        // mess drawing on screen

        if (!mPageFlip.isAnimating() &&

                mPageFlip.getFirstPage() != null) {

            mPageFlip.onFingerDown(x, y);

        }

    }



    /**

     * Handle finger moving event

     *

     * @param x finger x coordinate

     * @param y finger y coordinate

     */

    public void onFingerMove(float x, float y) {

        if (mPageFlip.isAnimating()) {

            // nothing to do during animating

        }

        else if (mPageFlip.canAnimate(x, y)) {

            // if the point is out of current page, try to start animating

            onFingerUp(x, y);

        }

        // move page by finger

        else if (mPageFlip.onFingerMove(x, y)) {

            try {

                mDrawLock.lock();

                if (mPageRender != null &&

                        mPageRender.onFingerMove(x, y)) {

                    requestRender();

                }

            }

            finally {

                mDrawLock.unlock();

            }

        }

    }



    /**

     * Handle finger up event and start animating if need

     *

     * @param x finger x coordinate

     * @param y finger y coordinate

     */

    public void onFingerUp(float x, float y) {

        if (!mPageFlip.isAnimating()) {

            mPageFlip.onFingerUp(x, y, mDuration);

            try {

                mDrawLock.lock();

                if (mPageRender != null &&

                        mPageRender.onFingerUp(x, y)) {

                    requestRender();

                }

            }

            finally {

                mDrawLock.unlock();

            }

        }

    }



    /**

     * Draw frame

     *

     * @param gl OpenGL handle

     */

    @Override

    public void onDrawFrame(GL10 gl) {

        try {

            mDrawLock.lock();

            if (mPageRender != null) {

                mPageRender.onDrawFrame();

            }

        }

        finally {

            mDrawLock.unlock();

        }

    }



    /**

     * Handle surface is changed

     *

     * @param gl OpenGL handle

     * @param width new width of surface

     * @param height new height of surface

     */

    @Override

    public void onSurfaceChanged(GL10 gl, int width, int height) {

        try {
            mPageFlip.onSurfaceChanged(width, height);
            int pageNo = mPageRender.getPageNo();

            if(!(mPageRender instanceof SinglePageRender)) {

                mPageRender.release();

                mPageRender = new SinglePageRender(getContext(),

                        mPageFlip,

                        mHandler,

                        pageNo);

            }
            // let page render handle surface change

            mPageRender.onSurfaceChanged(width, height);
        }
        catch (PageFlipException e) {

            Log.e(TAG, "Failed to run PageFlipFlipRender:onSurfaceChanged");

        }

    }



    /**

     * Handle surface is created

     *

     * @param gl OpenGL handle

     * @param config EGLConfig object

     */

    @Override

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        try {

            mPageFlip.onSurfaceCreated();

        }

        catch (PageFlipException e) {

            Log.e(TAG, "Failed to run PageFlipFlipRender:onSurfaceCreated");

        }

    }



    /**

     * Create message handler to cope with messages from page render,

     * Page render will send message in GL thread, but we want to handle those

     * messages in main thread that why we need handler here

     */

    private void newHandler() {

        mHandler = new Handler() {

            public void handleMessage(Message msg) {

                switch (msg.what) {

                    case PageRender.MSG_ENDED_DRAWING_FRAME:

                        try {

                            mDrawLock.lock();

                            // notify page render to handle ended drawing

                            // message

                            if (mPageRender != null &&

                                    mPageRender.onEndedDrawing(msg.arg1)) {

                                requestRender();

                            }

                        }

                        finally {

                            mDrawLock.unlock();

                        }

                        break;



                    default:

                        break;

                }

            }

        };

    }

}