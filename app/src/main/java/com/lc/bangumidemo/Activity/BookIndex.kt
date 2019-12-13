package com.lc.bangumidemo.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.os.Message
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import com.lc.bangumidemo.KT.PageUtil.startact
import com.lc.bangumidemo.KT.PagesizeUtil.txttolist
import com.lc.bangumidemo.KT.bookDetail
import com.lc.bangumidemo.KT.fontsize
import com.lc.bangumidemo.KT.linesize
import com.lc.bangumidemo.MyRetrofit.ResClass.BookContent
import com.lc.bangumidemo.MyRetrofit.ResClass.BookDetail
import com.lc.bangumidemo.MyRetrofit.Retrofit.Retrofitcall
import com.lc.bangumidemo.R
import com.lc.bangumidemo.Sqlite.*
import com.lc.bangumidemo.Sqlite.Bookinsert.insert
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.bookindex.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import java.net.URL
class BookIndex : BaseActivity() {
   lateinit var contentView: LinearLayout
    override fun setRes(): Int {
        return R.layout.bookindex
    }
    override fun initlistener() {
        super.initlistener()
        startread.setOnClickListener { loadbookdatatopage(bookDetail,0) }
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
            loadbookdatatopage(data,position)
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



    /*
    *加载书本页面数据入口
     */
    private fun loadbookdatatopage(book:BookDetail?,positon:Int) {
        //每次启动前查询是否有记录 同时记录当前位置
        var record=Nvdetil(null,bookDetail?.data?.name, bookDetail?.data?.author,null,null,null,null)
        var dbhelper = MyDatabaseHelper(this, "bookindexssss.db", null, 1)
        var datahelp = Bookindexdatabase(this, "bookdatassss.db", null, 1)

        val res=SqlUtil.findbookisexist(dbhelper,record)
        //
        val mHamdler1 = object : Handler() {

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    2 -> {
                        var result= msg.obj as BookContent
                        var string= result.content.toString()
                       var list =txttolist(string,this@BookIndex, fontsize, linesize)
                        //插入数据
                        var reco=Nvdetil(null,bookDetail?.data?.name, bookDetail?.data?.author,bookDetail?.list?.size,positon,0,string)
                        Bookinsert.insert(datahelp,reco)
                        if(res==null) {
                              //如果不存在 则插入一条记录
                            var reco=Nvdetil(null,bookDetail?.data?.name, bookDetail?.data?.author,bookDetail?.list?.size,positon,0,string)
                            SqlUtil.recordbookindex(dbhelper,reco)
                            dbhelper.close()
                            startact(this@BookIndex,list)
                        }
                        else{
                            if(positon==0) startact(this@BookIndex,list)
                            else
                            {
                                var reco=Nvdetil(null,bookDetail?.data?.name, bookDetail?.data?.author,bookDetail?.list?.size,positon,0,string)
                                Bookupdata.updata(dbhelper,reco)
                                dbhelper.close()
                                startact(this@BookIndex,list)
                            }
                        }
                        }
                    else -> {
                    }
                }
            }

        }
        Thread(Runnable {
            var message = Message()

            val call = Retrofitcall().getAPIServercontent().getCall(book!!.list[positon].url)
            call.enqueue(object : Callback<BookContent> {
                override fun onResponse(call: Call<BookContent>, response: Response<BookContent>) {
                    val st = response.body()
                    println(st)
                    message.obj=st
                    message.what=2
                    mHamdler1.sendMessage(message)
                }
                override fun onFailure(call: Call<BookContent>, t: Throwable) {
                    println("连接失败")
                    message.obj=null
                    message.what=2
                    mHamdler1.sendMessage(message)
                }

            })

        }).start()
    }

}