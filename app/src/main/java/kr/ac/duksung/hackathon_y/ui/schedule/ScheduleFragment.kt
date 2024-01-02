package kr.ac.duksung.hackathon_y.ui.schedule

import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import kr.ac.duksung.hackathon_y.databinding.FragmentScheduleBinding
import kr.ac.duksung.hackathon_y.ui.schedule.adapter.TimeRVAdapter
import java.util.Locale

class ScheduleFragment : Fragment() {
	private lateinit var binding: FragmentScheduleBinding
	private lateinit var timeRVAdapter: TimeRVAdapter
	private lateinit var sharedPreferences: SharedPreferences

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = FragmentScheduleBinding.inflate(inflater, container, false)
		sharedPreferences = requireContext().getSharedPreferences("time_pick", Context.MODE_PRIVATE) // 추가: SharedPreferences 초기화

		initRecyclerView()

		binding.btnAddTime.setOnClickListener { showTimePicker() }

		return binding.root
	}

	private fun initRecyclerView() {
		timeRVAdapter = TimeRVAdapter(requireContext(), sharedPreferences) // 수정: SharedPreferences 전달
		binding.recyclerReview.adapter = timeRVAdapter
		binding.recyclerReview.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
	}

	private fun showTimePicker() {
		val cal = Calendar.getInstance()
		val hour = cal.get(Calendar.HOUR_OF_DAY)
		val minute = cal.get(Calendar.MINUTE)

		val timePickerDialog = TimePickerDialog(
			requireContext(),
			{ _, selectedHour, selectedMinute ->
				val selectedTime = Time(selectedHour, selectedMinute)
				timeRVAdapter.addTime(selectedTime, formatTime(selectedTime))
			},
			hour,
			minute,
			true
		)
		timePickerDialog.show()
	}

	private fun initCalendarView() {
		binding.calendarView.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
			// 사용자가 캘린더에서 날짜를 선택했을 때 호출됩니다.
			// 여기서 선택한 날짜에 시간을 저장하거나 특별한 동작을 수행할 수 있습니다.

			// 예시: 선택한 날짜를 TextView에 표시
			val selectedDate = Calendar.getInstance()
			selectedDate.set(date.year, date.month, date.day)

			showTimePicker()
		})
	}

	private fun formatTime(time: Time): String {
		val calendar = Calendar.getInstance()
		calendar.set(Calendar.HOUR_OF_DAY, time.hour)
		calendar.set(Calendar.MINUTE, time.min)

		val simpleDateFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
		return simpleDateFormat.format(calendar.time)
	}
}
