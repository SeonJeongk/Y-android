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

        // 임의의 데이터 생성 (예: 5개의 알림 아이템)
        val dummyData = List(5) { index -> "알림 ${index + 1}" }

        val alarmRVAdapter = AlarmRVAdapter()
        // 어뎁터 설정
        binding.rvAlarm.adapter = alarmRVAdapter
        binding.rvAlarm.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // 데이터가 있으면 RecyclerView를 보이게 하고, 없으면 layout_nothing을 보이게 함
        if (dummyData.isNotEmpty()) {
            binding.layoutNothing.visibility = View.GONE
            binding.rvAlarm.visibility = View.VISIBLE
        } else {
            binding.layoutNothing.visibility = View.VISIBLE
            binding.rvAlarm.visibility = View.GONE
        }

        return binding.root
    }

}