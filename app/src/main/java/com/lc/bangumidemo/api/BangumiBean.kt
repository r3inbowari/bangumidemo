package com.lc.bangumidemo.api

data class ContentResult(
    val content: List<String>,
    val mum: String,
    val message: String,
    val code: Int,
    val list: List<String>
)

data class DetailResult(
    val data: DetailData,
    val list: List<DetailList>,
    val message: String,
    val code: Int
)

data class SearchResult(
    val list: List<DetailData>,
    val message: String,
    val code: Int
)

data class DetailData(
    val name: String,
    val cover: String,
    val introduce: String,
    val Release: String,
    val genre: String,
    val time: String,
    val director: String,
    val performer: String,
    val region: String,
    val Language: String,
    val author: String,
    val status: String,
    val tag: String
)

data class DetailList(
    val m3u8url: String,
    val onlineurl: String,
    val download: String,
    val num: String,
    val name: String,
    val url: String
)
