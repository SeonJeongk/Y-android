package kr.ac.duksung.hackathon_y

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.ac.duksung.hackathon_y.ui.meeting.MeetingFragment

class MainActivity : AppCompatActivity() {

    private val meetingFragment = MeetingFragment()
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // MainFragment로 시작
        replaceFragment(meetingFragment)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tapMeeting -> {
                    replaceFragment(meetingFragment)
                }
                R.id.tapTeam -> {
                }
                R.id.tapAlram -> {
                }
                R.id.tapSchedule -> {
                }

            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}