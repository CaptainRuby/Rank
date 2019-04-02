package com.ruby.rank.common.net

import com.ruby.rank.model.SearchResult
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

object ServiceAPI {

    const val root = "https://api.bgm.tv/"

    const val pic = "https://lain.bgm.tv/"

    interface Search {

        /**
         * 获取验证码
         * @param keywords 关键
         * @param type 类型
         * @return
         */
        @GET("search/subject/{keywords}")
        fun get(@Path("keywords") keywords: String, @Query("type") type: Int): Observable<SearchResult>
    }

    interface Image {

        /**
         * 获取封面图
         * @param url 地址
         * @return
         */
        @GET("pic/cover/{url}")
        fun get(@Path("url") url: String): Observable<ResponseBody>
    }

}