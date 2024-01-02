package kr.ac.duksung.hackathon_y.data.remote

import android.util.Log
import android.widget.Toast
import kr.ac.duksung.hackathon_y.data.entities.User
import kr.ac.duksung.hackathon_y.utils.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthService {
    private lateinit var loginView: LoginView

    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }

    // 로그인 관리
    fun login(user: User){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        // API 호출 및 enqueue로 응답 처리
        authService.login(user).enqueue(object: Callback<AuthResponse> {

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {   // 응답 옴
                Log.d("SIGNUP/SUCCESS", response.toString())
                val resp: AuthResponse = response.body()!!
                when(val code = resp.resultCode){
                    "SUCCESS" -> {
                        Log.d("Auth", "login success")
                        loginView.onLoginSuccess(code, resp.result!!.userId)    // 로그인 성공
                    }
                    else-> {
                        Log.d("Auth", "login Fail")
                        loginView.onLoginFailure(code, resp.resultCode)  //로그인 실패
                    }

                }
            }
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {    // 응답이 안 옴
                Log.d("LOGIN/FAILURE", t.message.toString())
                Toast.makeText(loginView.getContext(), "API 요청 실패", Toast.LENGTH_SHORT).show()
            }
        })
        Log.d("LOGIN", "로그인 AuthService 실행")
    }

}