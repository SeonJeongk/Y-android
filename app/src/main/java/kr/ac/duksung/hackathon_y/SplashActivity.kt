package kr.ac.duksung.hackathon_y
// SplashActivity.kt
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kr.ac.duksung.hackathon_y.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    private val delayMillis: Long = 3000 // 3초 딜레이

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        // 3초 딜레이 이후 LoginActivity로 이동
        Handler().postDelayed({
//            val intent = Intent(this, MainActivity::class.java)
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, delayMillis)
    }
}
