package com.lc.bangumidemo.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.os.Message
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.lc.bangumidemo.KT.*
import com.lc.bangumidemo.MyRetrofit.ResClass.BookDetail
import com.lc.bangumidemo.MyRetrofit.Retrofit.Retrofitcall
import com.lc.bangumidemo.R
import com.lc.bangumidemo.Sqlite.*

import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.bookindex.*
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.URL
import java.sql.SQLOutput
import java.util.*

class BookIndex : BaseActivity() {
   lateinit var contentView: LinearLayout
    override fun setRes(): Int {
        return R.layout.bookindex
    }
    override fun initlistener() {
        super.initlistener()
        startread.setOnClickListener {

            var db=MyDatabaseHelper(this,"bookstore",null,1)
            var selectindex = Selectclass(bookDetail!!.data.name, bookDetail!!.data.author, bookDetail!!.list.size)
            Bookselect.selectalldata(db)
            var returnsult=Bookselect.selectindex(db,selectindex)
            val timerTask = object : TimerTask() {
                override fun run() {
                    Mapinit(this@BookIndex,returnsult)
                    Mapupdata(this@BookIndex,returnsult)
                    Bookselect.selectalldata(db)
                    db.close()
                    startActivity<testclass>()
                }
            }
            val mTimer = Timer()
            mTimer.schedule(timerTask, 2000)

        }
        cell_title_view2.setOnClickListener {
            findViewById<FoldingCell>(R.id.folding_cell2).toggle(false)
            contentView = findViewById(R.id.linear)

        }
    }

    override fun initaction() {
        super.initaction()
        var bundle=intent.extras
        val urls=bundle!!.getString("url")
        val position=bundle!!.getInt("position")
        val call = Retrofitcall().getAPIServerdetail().getCall(urls)
        call.enqueue(object: Callback<BookDetail> {
            override fun onFailure(call: Call<BookDetail>, t: Throwable) {
                println("连接失败")
            }

            override fun onResponse(
                call: Call<BookDetail>,
                response: Response<BookDetail>
            ) {
                val st = response.body()
                println(st)
                loaddata(st)
                loadlist(st)

            }
        })
    }
    fun loadlist(data:BookDetail?){
        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,R.layout.textview, data?.getbooknum() as MutableList<String>)
        listview.adapter=adapter
        listview.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,data.getbooknum()[position],Toast.LENGTH_LONG).show()
            //先查询是否存在数据
            var db=MyDatabaseHelper(this,"bookstore",null,1)
            var selectdata = Selectclass(bookDetail!!.data.name, bookDetail!!.data.author, bookDetail!!.list.size)
            var resultnow= Bookselect.selectbookdata(db,selectdata,position)
            if (resultnow==null)loadbookdatatopage(this,data,position)
            if(position!=0)
            {
                var resultpre = Bookselect.selectbookdata(db, selectdata, position - 1)
                if (resultpre == null) loadbookdatatopage(this, data, position - 1)
            }
                var resultnex= Bookselect.selectbookdata(db,selectdata,position+1)
            if (resultnex==null)loadbookdatatopage(this,data,position+1)
            var updata=Bookindexclass(null, bookDetail!!.data.author, bookDetail!!.data.name,
                position, 0, bookDetail!!.list.size,position,0)
            Bookupdata.updata(db,updata)
            //索引更新，加载内容，重映射 (还剩重映射然后启动)
            var selectindex = Selectclass(bookDetail!!.data.name, bookDetail!!.data.author, bookDetail!!.list.size)
            var returnsult=Bookselect.selectindex(db,selectindex)
            val timerTask = object : TimerTask() {
                override fun run() {
                    Mapinit(this@BookIndex,returnsult)
                    Mapupdata(this@BookIndex,returnsult)
                    Bookselect.selectalldata(db)
                    db.close()
                    startActivity<testclass>()
                }
            }
            val mTimer = Timer()
            mTimer.schedule(timerTask, 2000)
        }
    }
    fun loaddata(data:BookDetail?)
    {
        if(data==null)return
        bookDetail=data
        initThreadtoupdatapicture(data.data.cover)
        bookname.setText(data.data.name)
        bookauthor.setText(data.data.author)
        booknum.setText(data.data.num)
        bookstatus.setText(data.data.status)
        booktime.setText(data.data.time)
        booktag.setText(data.data.tag)
        text_view.setText(data.data.introduce)
        //查询是否存在索引
        var db=MyDatabaseHelper(this,"bookstore",null,1)
        var selectindex = Selectclass(bookDetail!!.data.name, bookDetail!!.data.author, bookDetail!!.list.size)
        var returnsult=Bookselect.selectindex(db,selectindex)
        if(returnsult==null)
        {
                loadbookdatatopage(this,data,0)
                loadbookdatatopage(this,data,1)
                loadbookdatatopage(this,data,2)
                var insert=Bookindexclass(null, bookDetail!!.data.author, bookDetail!!.data.name,0,0, bookDetail!!.list.size,0,0)
                Bookinsert.insertindex(db,insert)
        }else
        {
            hardcontentindex=returnsult.hardcontentindex
            hardpageindex=returnsult.hardpageindex
        }
        //如果不存在则加载前三章
        //插入索引


    }
    override fun startaction() {
        super.startaction()

    }
    private fun initThreadtoupdatapicture(urls:String) {
        val mHamdler1 = object : Handler() {

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    1 -> {
                        if(msg.obj!=null) {
                            val picture = msg.obj as Bitmap
                            var drawable=BitmapDrawable(resources, picture);
                            detailview.background=drawable
                            detailview.alpha= 0.5F
                            bookcover.setImageBitmap(picture)
                        }
                    }
                    else -> {
                        val dafultmap = BitmapFactory.decodeResource(this@BookIndex.resources, R.drawable.ic_launcher_background)
                        detailview.setBackgroundResource(R.drawable.ic_launcher_background)
                        detailview.alpha= 0.5F
                        bookcover.setImageBitmap(dafultmap)
                    }
                }
            }
        }
        Thread(Runnable {
            val message = Message()
            var image: Bitmap? = null
            var url: URL? = null
            try {
                url = URL(urls)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            var io: InputStream? = null
            try {
                io = url!!.openConnection().getInputStream()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            image = BitmapFactory.decodeStream(io)
            message.what = 1
            message.obj = image
            mHamdler1.sendMessage(message)
            //还要处理异常
        }).start()
    }



}