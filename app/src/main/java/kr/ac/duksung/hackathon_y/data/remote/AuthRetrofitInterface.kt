package kr.ac.duksung.hackathon_y.data.remote

import kr.ac.duksung.hackathon_y.data.entities.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/api/users/login")
    fun login(@Body user: User): Call<AuthResponse>
}