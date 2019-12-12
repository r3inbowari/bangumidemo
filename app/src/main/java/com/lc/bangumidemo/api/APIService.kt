package com.lc.bangumidemo.api

import io.reactivex.Observable
import retrofit2.http.*

const val BASE_URL = "http://api.pingcc.cn/"

interface APIService {

    // 动漫
    @GET(BASE_URL)
    fun getBangumis(@Query("ysname") ysname: String): Observable<SearchResult>

    @GET(BASE_URL)
    fun getBangumiDetail(@Query("ysurl") ysurl: String): Observable<DetailResult>

    // 小说
    @GET(BASE_URL)
    fun getNovels(@Query("xsname") xsname: String): Observable<SearchResult>

    @GET(BASE_URL)
    fun getNovelDetail(@Query("xsurl1") xsurl1: String): Observable<DetailResult>

    @GET(BASE_URL)
    fun getNovelContent(@Query("xsurl2") xsurl2: String): Observable<ContentResult>
}
