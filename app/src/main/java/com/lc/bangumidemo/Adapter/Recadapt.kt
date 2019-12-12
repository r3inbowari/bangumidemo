package com.lc.bangumidemo.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lc.bangumidemo.MyRetrofit.ResClass.Bookdata
import com.lc.bangumidemo.R
import java.io.IOException
import java.io.InputStream
import java.net.URL


class Recadapt(private val list: List<Bookdata>,private val context : Context) : RecyclerView.Adapter<Recadapt.ViewHolder>() {
    private var mOnItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carditem, parent, false)
        println(parent)
        return ViewHolder(view)
    }

    // 点击事件接口
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("position:$position")
        holder.name.setText(list[position].name)
        holder.time.setText(list[position].time)
        holder.author.setText(list[position].author)
        holder.num.setText(list[position].name)
        holder.tag.setText(list[position].tag)
        holder.statues.setText(list[position].status)

        var introduce=list[position].introduce
        if (mOnItemClickListener != null) {
            holder.cardView.setOnClickListener {
                mOnItemClickListener!!.onItemClick(
                    holder.itemView,
                    position
                )
            }
        }
        index = position
        initThreadtoupdatapicture(holder, position, list)
    }

    private fun initThreadtoupdatapicture(holder: ViewHolder, position: Int, list: List<Bookdata>) {
        val mHamdler1 = object : Handler() {

            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                when (msg.what) {
                    1 -> {
                        if(msg.obj!=null) {
                            val picture = msg.obj as Bitmap
                            holder.cover.setImageBitmap(picture)
                        }
                    }
                    else -> {
                        val dafultmap = BitmapFactory.decodeResource(context.resources, R.drawable.simple_player_bg_normal)
                        holder.cover.setImageBitmap(dafultmap)
                    }
                }
            }
        }
        Thread(Runnable {
            val message = Message()
            val urls = list[position].cover
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


    override fun getItemCount(): Int {
        println("COUNT:" + list.size)
        return list.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cover: ImageView
        var name: TextView
        var time: TextView
        var author: TextView
        var num: TextView
        var tag: TextView
        var statues: TextView
        var cardView : CardView
        init {
            cover = itemView.findViewById(R.id.cover)
            name = itemView.findViewById(R.id.name)
            time = itemView.findViewById(R.id.time)
            num = itemView.findViewById(R.id.num)
            tag = itemView.findViewById(R.id.tag)
            author = itemView.findViewById(R.id.author)
            statues = itemView.findViewById(R.id.status)
            cardView= itemView.findViewById(R.id.cardview)
        }
    }

    companion object {
        var index = 0
    }


}