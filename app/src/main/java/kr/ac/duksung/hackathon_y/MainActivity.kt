package kr.ac.duksung.hackathon_y

import android.app.Activity
import android.app.AlarmManager
import ScheduleFragment
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.ac.duksung.hackathon_y.ui.meeting.MeetingFragment
import kr.ac.duksung.hackathon_y.ui.alarm.AlarmFragment
import kr.ac.duksung.hackathon_y.ui.alarm.AlarmManagerUtil
import kr.ac.duksung.hackathon_y.ui.alarm.AlarmReceiver
import kr.ac.duksung.hackathon_y.ui.TeamManagement.TeamManagementFragment
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {

    private val meetingFragment = MeetingFragment()
    private val alarmFragment = AlarmFragment()
    private val teamManagementFragment=TeamManagementFragment()
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 앱이 처음 실행될 때 호출
        AlarmReceiver.setupNotificationChannel(this)
        AlarmManagerUtil.setRepeatingAlarm(this)

        // 알림 권한
        checkNotificationPermission()

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // MainFragment로 시작
        replaceFragment(meetingFragment)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tapMeeting -> {
                    replaceFragment(meetingFragment)
                }
                R.id.tapTeam -> {
                    replaceFragment(teamManagementFragment)
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

    private fun checkNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // 권한 없을시 요청
            ActivityCompat.requestPermissions(
                this as Activity,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                AlarmReceiver.REQUEST_NOTIFICATION_PERMISSION
            )
        }
    }
}