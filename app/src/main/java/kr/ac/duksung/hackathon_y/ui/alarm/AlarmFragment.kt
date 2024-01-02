package kr.ac.duksung.hackathon_y.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.duksung.hackathon_y.databinding.FragmentAlarmBinding

class AlarmFragment() : Fragment() {

    private lateinit var binding : FragmentAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(inflater, container, false)

        binding.actionbar.tvTitle.visibility = View.VISIBLE
        binding.actionbar.tvTitle.text = "알림"

        val alarmRVAdapter = AlarmRVAdapter()
        // 어뎁터 설정
        binding.rvAlarm.adapter = alarmRVAdapter
        binding.rvAlarm.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

}