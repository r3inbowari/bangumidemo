package com.lc.bangumidemo.Adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lc.bangumidemo.MyRetrofit.ResClass.Bookdata
import com.lc.bangumidemo.R
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.imageResource
import java.io.IOException
import java.io.InputStream
import java.net.URL
import kotlin.math.log


class Recadapt(private val list: List<Bookdata>, private val context: Context) :
    RecyclerView.Adapter<Recadapt.ViewHolder>() {
    private var picturesize = 0
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

    fun stdeal(string: String?): String {
        if (string == null) {
            return "无"
        }
        if (string.length > 8) {
            return string.substring(0, 8) + "..."
        } else {
            return string
        }
    }

    fun stnull(string: String?): String {
        if (string == null) {
            return "无"
        }
        return string
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("position:$position")
        holder.imageUrl=list[position].cover
        holder.name.setText("书名:" + stnull(list[position].name))
        holder.time.setText("更新时间:" + stnull(list[position].time))
        holder.author.setText("作者:" + stnull(list[position].author))
        holder.num.setText("最新章节:" + stdeal(list[position].num))
        holder.tag.setText("类型:" + stnull(list[position].tag))
        holder.statues.setText("状态:" + stnull(stdeal(list[position].status)))
        if (mOnItemClickListener != null) {
            holder.cardView.setOnClickListener {
                mOnItemClickListener!!.onItemClick(
                    holder.itemView,
                    position
                )
            }
        }
        if (picturesize<list.size) {
            initThreadtoupdatapicture(holder)
            picturesize++
        }
    }
    private fun initThreadtoupdatapicture(holder: ViewHolder) {
        val mHamdler1 = object : Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                val picture = msg.obj as Bitmap
                if (picture != null) {
                        holder.cover.setImageBitmap(picture)
                }


            }
        }
        Thread(Runnable {
            var message = Message()
            try {
                var urls = holder.imageUrl
                var image: Bitmap?
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
            } catch (e: Exception) {
                println(e)
                mHamdler1.sendEmptyMessage(1)
            }
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
        var cardView: CardView
        lateinit var imageUrl: String

        init {
            cover = itemView.findViewById(R.id.cover)
            name = itemView.findViewById(R.id.name)
            time = itemView.findViewById(R.id.time)
            num = itemView.findViewById(R.id.num)
            tag = itemView.findViewById(R.id.tag)
            author = itemView.findViewById(R.id.author)
            statues = itemView.findViewById(R.id.status)
            cardView = itemView.findViewById(R.id.cardview)
        }
    }

    companion object {
        var index = 0
    }


}