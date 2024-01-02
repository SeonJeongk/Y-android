package kr.ac.duksung.hackathon_y.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.ac.duksung.hackathon_y.MainActivity
import kr.ac.duksung.hackathon_y.data.entities.User
import kr.ac.duksung.hackathon_y.data.remote.AuthService
import kr.ac.duksung.hackathon_y.data.remote.LoginView
import kr.ac.duksung.hackathon_y.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginView {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 처리
        binding.btLogin.setOnClickListener {
            login()
        }
    }

    // 로그인 유효성 검사
    private fun login() {
        // 이메일, 비밀번호가 유효하면 db에 정보 유무 확인 후 로그인
        val studentId: String = binding.etId.text.toString()
        val password: String = binding.etPw.text.toString()

        val authService = AuthService()
        authService.setLoginView(this)

        authService.login(User(studentId, password))
    }

    private fun saveId(userId: Int) {
        val spf = getSharedPreferences("auth", MODE_PRIVATE)
        val editor = spf.edit()

        editor.putInt("id", userId)
        editor.apply()
    }

    private fun startMainActivity() {
        val Intent = Intent(this, MainActivity::class.java)
        startActivity(Intent)
    }

    // 로그인 성공 시 메인 화면으로 돌아감
    override fun onLoginSuccess(code: String, userId: Int) {
        when (code) {
            "SUCCESS" -> {
                saveId(userId)
                startMainActivity()
                Log.d("login 성공", "유저 id : $userId")
            }
        }
    }

    override fun onLoginFailure(code: String, message: String) {
        when (code) {
            "INVALID_LOGIN_INFO" -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }else -> {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getContext(): Context {
        // 여기서 해당 뷰의 Context를 반환하도록 구현
        return this
    }
}