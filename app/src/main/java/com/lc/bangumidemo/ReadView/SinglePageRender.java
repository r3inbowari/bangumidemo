package com.lc.bangumidemo.ReadView;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import com.eschao.android.widget.pageflip.Page;
import com.eschao.android.widget.pageflip.PageFlip;
import com.eschao.android.widget.pageflip.PageFlipState;
import com.lc.bangumidemo.KT.KtactivityKt;
import com.lc.bangumidemo.KT.PageUtil;
import com.lc.bangumidemo.KT.PagesizeUtil;
import com.lc.bangumidemo.MyRetrofit.ResClass.BookDetail;
import com.lc.bangumidemo.Sqlite.Bookindexdatabase;
import com.lc.bangumidemo.Sqlite.Bookselect;
import com.lc.bangumidemo.Sqlite.Bookupdata;
import com.lc.bangumidemo.Sqlite.MyDatabaseHelper;
import com.lc.bangumidemo.Sqlite.Nvdetil;
import com.lc.bangumidemo.Sqlite.SqlUtil;

import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.KAnnotatedElement;


/**

 * Single page render

 * <p>

 * Every page need 2 texture in single page mode:

 * <ul>

 *     <li>First texture: current page content</li>

 *     <li>Back texture: back of front content, it is same with first texture

 *     </li>

 *     <li>Second texture: next page content</li>

 * </ul>

 * </p>

 *

 * @author eschao

 */



public class SinglePageRender extends PageRender {
    public static int page=0;
    public static int mytextsize=KtactivityKt.getFontsize();
    public static List<String> txtpa = new ArrayList<String>();
    private Context contextm=null;
    public static void setMytextsize(int mytextsize) {
        SinglePageRender.mytextsize = mytextsize;
    }

    public SinglePageRender(Context context, PageFlip pageFlip,

                            Handler handler, int pageNo) {

        super(context, pageFlip, handler, pageNo);
        contextm=context;
        pageFlip.enableClickToFlip(false);

    }



    /**

     * Draw frame

     */

    public void onDrawFrame() {

        // 1. delete unused textures

        mPageFlip.deleteUnusedTextures();

        Page page = mPageFlip.getFirstPage();



        // 2. handle drawing command triggered from finger moving and animating

        if (mDrawCommand == DRAW_MOVING_FRAME ||

                mDrawCommand == DRAW_ANIMATING_FRAME) {

            // is forward flip

            if (mPageFlip.getFlipState() == PageFlipState.FORWARD_FLIP) {

                // check if second texture of first page is valid, if not,

                // create new one

                if (!page.isSecondTextureSet()) {
                    this.page=mPageNo + 1;
                    drawPage(mPageNo + 1);

                    page.setSecondTexture(mBitmap);

                }

            }

            // in backward flip, check first texture of first page is valid

            else if (!page.isFirstTextureSet()) {
                this.page=mPageNo-1;
                drawPage(--mPageNo);


                page.setFirstTexture(mBitmap);

            }



            // draw frame for page flip

            mPageFlip.drawFlipFrame();

        }

        // draw stationary page without flipping

        else if (mDrawCommand == DRAW_FULL_PAGE) {

            if (!page.isFirstTextureSet()) {
                this.page=mPageNo;
                drawPage(mPageNo);

                page.setFirstTexture(mBitmap);

            }



            mPageFlip.drawPageFrame();

        }



        // 3. send message to main thread to notify drawing is ended so that

        // we can continue to calculate next animation frame if need.

        // Remember: the drawing operation is always in GL thread instead of

        // main thread

        Message msg = Message.obtain();

        msg.what = MSG_ENDED_DRAWING_FRAME;

        msg.arg1 = mDrawCommand;

        mHandler.sendMessage(msg);

    }



    /**

     * Handle GL surface is changed

     *

     * @param width surface width

     * @param height surface height

     */

    public void onSurfaceChanged(int width, int height) {

        // recycle bitmap resources if need

        if (mBackgroundBitmap != null) {

            mBackgroundBitmap.recycle();

        }



        if (mBitmap != null) {

            mBitmap.recycle();

        }



        // create bitmap and canvas for page

        //mBackgroundBitmap = background;

        Page page = mPageFlip.getFirstPage();

        mBitmap = Bitmap.createBitmap((int)page.width(), (int)page.height(),

                Bitmap.Config.ARGB_8888);

        mCanvas.setBitmap(mBitmap);

        LoadBitmapTask.get(mContext).set(width, height, 1);

    }



    /**

     * Handle ended drawing event

     * In here, we only tackle the animation drawing event, If we need to

     * continue requesting render, please return true. Remember this function

     * will be called in main thread

     *

     * @param what event type

     * @return ture if need render again

     */

    public boolean onEndedDrawing(int what) {

        if (what == DRAW_ANIMATING_FRAME) {

            boolean isAnimating = mPageFlip.animating();

            // continue animating

            if (isAnimating) {

                mDrawCommand = DRAW_ANIMATING_FRAME;

                return true;

            }

            // animation is finished

            else {

                final PageFlipState state = mPageFlip.getFlipState();

                // update page number for backward flip

                if (state == PageFlipState.END_WITH_BACKWARD) {

                    // don't do anything on page number since mPageNo is always

                    // represents the FIRST_TEXTURE no;

                }

                // update page number and switch textures for forward flip

                else if (state == PageFlipState.END_WITH_FORWARD) {

                    mPageFlip.getFirstPage().setFirstTextureWithSecond();
                    page=mPageNo;
                    mPageNo++;


                }



                mDrawCommand = DRAW_FULL_PAGE;

                return true;

            }

        }

        return false;

    }



    /**

     * Draw page content

     *

     * @param number page number

     */
    public static int num =-1;
    private void drawPage(int number) {
        /**
         * 画之前查询当前游标加载数据
         */
        //每次进来更新游标


        //每次启动前查询是否有记录 同时记录当前位置
        MyDatabaseHelper dbhelper =new  MyDatabaseHelper(contextm, "bookindexssss.db", null, 1);
        Bookindexdatabase datahelp = new Bookindexdatabase(contextm, "bookdatassss.db", null, 1);

        String name=KtactivityKt.getBookDetail().getData().getName();
        String author =KtactivityKt.getBookDetail().getData().getAuthor();
        int pagesize =KtactivityKt.getBookDetail().getList().size();
        //获取游标
        Nvdetil r =new Nvdetil(null, name,author,pagesize,null,null,null);
        Nvdetil retu = SqlUtil.findbookisexist(dbhelper,r);
        int pageindex=retu.getPageindex();
        int contentindex=retu.getContentindex();

        //
        Nvdetil up =new Nvdetil(null, name,author,pagesize,pageindex,number,null);
        Bookupdata.updata(dbhelper,up);
        //获取数据
        Nvdetil getdata =new Nvdetil(null, name,author,pagesize,pageindex,null,null);
        Nvdetil nv=Bookselect.select(datahelp,getdata);
        String txe=nv.getContent();
        List<String> m=PagesizeUtil.INSTANCE.txttolist(txe,contextm,KtactivityKt.getFontsize(),KtactivityKt.getLinesize());
        PageUtil.INSTANCE.clean();
        for(String i : m ){
            PageUtil.INSTANCE.loadtxt(i);
        }
        num=number;
        final int width = mCanvas.getWidth();

        final int height = mCanvas.getHeight();

        Paint p = new Paint();

        p.setFilterBitmap(true);



        // 1. draw background bitmap

        Bitmap background = LoadBitmapTask.get(mContext).getpicture(number);

        Rect rect = new Rect(0, 0, width, height);

        mCanvas.drawBitmap(background, null, rect, p);

        background.recycle();

        background = null;



        // 2. draw page number

        int fontSize = calcFontSize(80);

        p.setColor(Color.WHITE);

        p.setStrokeWidth(1);

        p.setAntiAlias(true);

        p.setShadowLayer(5.0f, 8.0f, 8.0f, Color.BLACK);

        p.setTextSize(fontSize);

        String text = String.valueOf(number);

        float textWidth = p.measureText(text);

        float y = height - p.getTextSize() - 20;

          mCanvas.drawText(text, (width - textWidth) / 2, y, p);

        //draw txt


        String txt= KtactivityKt.getPagetxt().get(number-1);
        Paint a =new Paint();
        float si =calcFontSize(mytextsize);
        a.setTextSize(si);
        float w1=si*txt.length();
        float w = a.measureText(txt);
        float widthsub = width-si;
        int linecount = (int) (w1/widthsub)+1;
        int linesize = (int) (widthsub/si);
        int count=cuttxt(txt,linecount,linesize);//返回加载多少个字能填满整个屏幕
        float h = a.getTextSize();
        for(int i=0;i<linecount;i++) {
            mCanvas.drawText(txtpa.get(i), si/2, (height-16*(h+40))/2+(height-16*(h+40))/4 + i*(h+40), a);
        }

        if (number <= 1) {

            String firstPage = "The First Page";

            p.setTextSize(calcFontSize(16));

            float w2 = p.measureText(firstPage);

            float h2 = p.getTextSize();

        }

        else if (number >= MAX_PAGES) {

            String lastPage = "The Last Page";

            p.setTextSize(calcFontSize(16));

            float w3 = p.measureText(lastPage);

            float h3 = p.getTextSize();

            mCanvas.drawText(lastPage, (width - w3) / 2, y + 5 + h3, p);

        }

    }
    public static float cal(int size, Context context)
    {
        return  (int)(size * context.getResources().getDisplayMetrics()

                .scaledDensity);
    }
    public static int getcount(Context context,int width,int height)
    {
        String st="吖";
        float si =cal(mytextsize,context);
        float w1=si*st.length();
        float widthsub = width-si;
        int linecount = (int) (w1/widthsub)+1;
        int linesize = (int) (widthsub/si);
        int count=cuttxtt(st,linecount,linesize);//返回加载多少个字能填满一行
        //显示行数
        int l=16;
        return l*count;
    }
    public static int cuttxt(String string,int linecount,int linesize)
    {
        txtpa.clear();
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
            txtpa.add(t);
        }
        return temp;
    }
    public static int cuttxtt(String string,int linecount,int linesize)
    {
        int subsize= string.length();
        int addsize= (linesize*linecount-subsize);
        int temp = subsize+addsize;

        return temp;
    }


    /**

     * If page can flip forward

     *

     * @return true if it can flip forward

     */

    public boolean canFlipForward() {

        return (mPageNo < MAX_PAGES);

    }



    /**

     * If page can flip backward

     *

     * @return true if it can flip backward

     */

    public boolean canFlipBackward() {

        if (mPageNo > 1) {

            mPageFlip.getFirstPage().setSecondTextureWithFirst();

            return true;

        }

        else {

            return false;

        }

    }

}