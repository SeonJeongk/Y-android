package kr.ac.duksung.hackathon_y.data.remote

import com.google.gson.annotations.SerializedName

data class AuthResponse(
        @SerializedName(value = "resultCode") val resultCode:String,
        @SerializedName(value = "result") val result: Result?   //null 처리 해야 같이 사용 가능
)

data class Result(
    @SerializedName(value = "userId") var userId : Int
)