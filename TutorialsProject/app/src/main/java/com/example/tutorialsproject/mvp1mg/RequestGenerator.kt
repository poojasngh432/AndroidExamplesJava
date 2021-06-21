package com.example.tutorialsproject.mvp1mg

import androidx.annotation.NonNull
import okhttp3.Request

class RequestGenerator {

    companion object {

        @JvmStatic
        fun get(@NonNull url: String): Request {
            val builder = Request.Builder().url(url)
            addDefaultHeaders(builder)
            return builder.build()
        }


        @JvmStatic
        private fun addDefaultHeaders(@NonNull builder: Request.Builder) {
            builder.header(HkpApi.Headers.ACCEPT_KEY, HkpApi.Headers.ACCEPT_VALUE)
            builder.addHeader(HkpApi.Headers.CONTENT_TYPE, HkpApi.Headers.CONTENT_TYPE_VALUE)

            addUserId(builder)
        }

        @JvmStatic
        private fun addUserId(@NonNull builder: Request.Builder) {
            val userId = "110"
            if (userId.equals("")) {
                builder.addHeader(HkpApi.Headers.USER_ID, userId)
            }
        }
    }

}