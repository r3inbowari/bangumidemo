package com.lc.bangumidemo.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.lc.bangumidemo.Adapter.Recadapt
import com.lc.bangumidemo.KT.list
import com.lc.bangumidemo.MyRetrofit.ResClass.BookResult
import com.lc.bangumidemo.MyRetrofit.Retrofit.Retrofitcall
import com.lc.bangumidemo.R

import kotlinx.android.synthetic.main.search.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception




class Searchactivity :BaseActivity() {
    lateinit var searchItem:MenuItem
    lateinit var searchView:SearchView
    override fun setRes(): Int {
        return R.layout.search
    }

    override fun initview() {
        super.initview()
        setSupportActionBar(toolbar_search)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        anmo.hide()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {android.R.id.home -> finish()}
        return super.onOptionsItemSelected(item)
    }

    fun initsearchlistener() {
        //

        searchView = searchItem.actionView as SearchView
        searchView!!.isSubmitButtonEnabled = false // 提交按钮
        searchView!!.queryHint = "少年, 要来个兔子么"
        searchView!!.onActionViewExpanded()
        searchView!!.isIconified = false
        searchView!!.clearFocus() // 收起键盘
        //
        val bundle = this.intent.extras //读取intent的数据给bundle对象
        val tag = bundle!!.getString("tag") //通过key得到value
        when(tag){
            "小说"->{searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    anmo.show()
                    list.clear()     //清除缓存
                    searchbook(query)//查找书本
                    searchView!!.clearFocus() // 收起键盘
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                return true
                }
            })}
            "影视"->{}
            "动漫"->{}
            "综合"->{}
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menusearch, menu)
        searchItem = menu!!.findItem(R.id.action_search)
        initsearchlistener()
        return super.onCreateOptionsMenu(menu)
    }
    fun initadapt(){

        var adapt = Recadapt(list,this)
        initlistener(adapt)
        listview.setItemViewCacheSize(50)
        listview.setLayoutManager(LinearLayoutManager (this@Searchactivity))
        listview.adapter = adapt
        adapt.notifyDataSetChanged()
    }
    private fun initlistener(adapter:Recadapt) {
        adapter.setOnItemClickListener(object :Recadapt.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                lockscreen(true)
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                //view!!.findViewById<CardView>(R.id.cardview)
                var urls= list[position].url
                if(urls!=null) {
                    var start = Intent(this@Searchactivity, BookDetailActivity::class.java)
                    var bundle = Bundle()
                    bundle.putString("url", urls)
                    bundle.putInt("position", position)
                    start.putExtras(bundle)
                    startActivity(start)
                }else{
                    var intent = Intent(this@Searchactivity, ErrorActivity::class.java)
                    intent.putExtra("msg","连接失败，请检查你的网络")
                    intent.putExtra("tag","Search_Activity")
                    anmo.hide()
                    startActivity(intent)
                }
            }
        })
    }
    fun searchbook(name: String?) {
        val mHamdler1 = object : Handler() {

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    2 -> {
                        try {
                            var result= msg.obj as BookResult
                            if(result!=null)
                            {
                                anmo.hide()
                                getbookdata(result)
                            }
                        }catch (e:Exception){
                            var intent = Intent(this@Searchactivity, ErrorActivity::class.java)
                            intent.putExtra("msg","网络错误")
                            intent.putExtra("tag","Search_Activity")
                            anmo.hide()
                            startActivity(intent)
                        }

                    }
                }
            }
        }
        Thread(Runnable {
            var message = Message()
            val call = name?.let { Retrofitcall().getAPIService().getCall(it) }
            if (call != null) {
                call.enqueue(object : Callback<BookResult> {
                    override fun onResponse(call: Call<BookResult>, response: Response<BookResult>) {
                        val st = response.body()
                        println(st)
                        message.obj=st
                        message.what=2
                        mHamdler1.sendMessage(message)
                    }

                    override fun onFailure(call: Call<BookResult>, t: Throwable) {
                        message.obj=null
                        message.what=2
                        mHamdler1.sendMessage(message)
                    }
                })
            }
        }).start()
    }
    fun getbookdata(result:BookResult) {

        val hand = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {

                    3 -> {
                        try {
                            for(i in result!!.list)
                            {
                                list.add(i)
                            }
                            initadapt()
                        }catch (e:Exception){
                            var intent = Intent(this@Searchactivity, ErrorActivity::class.java).setFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.putExtra("msg","无效的书源")
                            intent.putExtra("tag","Search_Activity")
                            anmo.hide()
                            startActivity(intent)
                        }

                    }
                }
            }
        }
        Thread(Runnable {
            hand.sendEmptyMessage(3)
        }).start()
    }

    override fun onRestart() {
        super.onRestart()
    }
}