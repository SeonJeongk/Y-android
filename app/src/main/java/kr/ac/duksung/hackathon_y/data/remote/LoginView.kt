package kr.ac.duksung.hackathon_y.data.remote

import android.content.Context

interface LoginView {
    fun onLoginSuccess(code: String, userId: Int)
    fun onLoginFailure(code: String, message: String)
    fun getContext(): Context
}