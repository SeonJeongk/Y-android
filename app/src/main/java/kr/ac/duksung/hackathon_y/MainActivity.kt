package kr.ac.duksung.hackathon_y

import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.ac.duksung.hackathon_y.ui.MeetingFragment
import kr.ac.duksung.hackathon_y.ui.alarm.AlarmFragment
import kr.ac.duksung.hackathon_y.ui.schedule.ScheduleFragment
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {

    private val meetingFragment = MeetingFragment()
    private val alarmFragment = AlarmFragment()
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
                    replaceFragment(alarmFragment)
                }
                R.id.tapSchedule -> {
	                replaceFragment(ScheduleFragment())
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