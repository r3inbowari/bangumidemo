package com.lc.bangumidemo.Activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Handler
import android.os.Message
import android.widget.ArrayAdapter
import com.lc.bangumidemo.Adapter.Recadapt
import com.lc.bangumidemo.KT.imglist
import com.lc.bangumidemo.MyRetrofit.ResClass.BookDetail
import com.lc.bangumidemo.MyRetrofit.ResClass.Bookdata
import com.lc.bangumidemo.MyRetrofit.Retrofit.Retrofitcall
import com.lc.bangumidemo.R
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.bookindex.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import java.net.URL

class BookIndex : BaseActivity() {
    override fun setRes(): Int {
        return R.layout.bookindex
    }

    override fun initlistener() {
        super.initlistener()
        cell_content_view2.setOnClickListener { findViewById<FoldingCell>(R.id.folding_cell2).toggle(false) }
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
        var adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,R.layout.textview, data?.list as MutableList<String>)
        listview.adapter=adapter
    }
    fun loaddata(data:BookDetail?)
    {
        if(data==null)return
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
}