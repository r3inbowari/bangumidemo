package com.lc.bangumidemo.MyRetrofit.Retrofit

import com.lc.bangumidemo.MyRetrofit.APIinterface.APIServerdetail
import com.lc.bangumidemo.MyRetrofit.APIinterface.APIServerread
import com.lc.bangumidemo.MyRetrofit.APIinterface.APIService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Retrofitcall {
    val SEARCH_BOOK_BASEURL = "http://api.pingcc.cn/"
    fun getAPIService(): APIService {


        val mRetrofit = Retrofit.Builder()
            .baseUrl(SEARCH_BOOK_BASEURL)
            .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return mRetrofit.create(APIService::class.java!!)
    }
    fun getAPIServerdetail(): APIServerdetail {
        val mRetrofit = Retrofit.Builder()
            .baseUrl(SEARCH_BOOK_BASEURL)
            .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return mRetrofit.create(APIServerdetail::class.java!!)
    }
    fun getAPIServercontent(): APIServerread {
        val mRetrofit = Retrofit.Builder()
            .baseUrl(SEARCH_BOOK_BASEURL)
            .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return mRetrofit.create(APIServerread::class.java!!)
    }
}