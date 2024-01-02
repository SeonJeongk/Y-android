package kr.ac.duksung.hackathon_y.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 여러 화면에서 공통적으로 쓰이는 형식이기 때문에 해당 파일에 따로 함수를 모아둔다

const val BASE_URL = "http://3.37.230.158:8080" //연결할 주소

fun getRetrofit(): Retrofit {
    val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    return retrofit
}