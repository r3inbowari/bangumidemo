package com.lc.bangumidemo.KT

import android.content.Context
import android.os.Handler
import android.os.Message
import com.lc.bangumidemo.MyRetrofit.ResClass.BookContent
import com.lc.bangumidemo.MyRetrofit.ResClass.BookDetail
import com.lc.bangumidemo.MyRetrofit.Retrofit.Retrofitcall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var isact :Boolean = true
fun loadbookdatatopage(context:Context,book: BookDetail?, positon:Int) {
    position=positon
    val mHamdler1 = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                2 -> {
                    var result= msg.obj as BookContent
                    var string= result.content.toString()
                    var list = PagesizeUtil.txttolist(string, context, fontsize, linesize)
                    PageUtil.startact(context, list)
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
