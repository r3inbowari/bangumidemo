package com.lc.bangumidemo.MyRetrofit.Retrofit

import com.lc.bangumidemo.MyRetrofit.APIinterface.APIService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Retrofitcall {

    fun getAPIService(): APIService {
        val SEARCH_BOOK_BASEURL = "http://api.pingcc.cn/"
        val mRetrofit: Retrofit
        mRetrofit = Retrofit.Builder()
            .baseUrl(SEARCH_BOOK_BASEURL)
            .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
        return mRetrofit.create(APIService::class.java!!)
    }
}