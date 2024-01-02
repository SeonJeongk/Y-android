import kr.ac.duksung.hackathon_y.ui.schedule.Time
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
	private var selectedDate: Calendar? = null  // 추가: 선택한 날짜를 저장하는 변수

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentScheduleBinding.inflate(inflater, container, false)
		sharedPreferences = requireContext().getSharedPreferences("time_pick", Context.MODE_PRIVATE)

		initRecyclerView()
		initCalendarView()

		binding.btnAddTime.setOnClickListener { showTimePicker() }

		return binding.root
	}

	private fun initRecyclerView() {
		timeRVAdapter = TimeRVAdapter(requireContext(), sharedPreferences)
		binding.recyclerReview.adapter = timeRVAdapter
		binding.recyclerReview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
	}

	private fun showTimePicker() {
		val cal = Calendar.getInstance()
		val hour = cal.get(Calendar.HOUR_OF_DAY)
		val minute = cal.get(Calendar.MINUTE)

		val timePickerDialog = TimePickerDialog(
			requireContext(),
			{ _, selectedHour, selectedMinute ->
				val selectedTime = Time(selectedHour.toString(), selectedMinute.toString())
				saveTime(selectedTime)
			},
			hour,
			minute,
			true
		)
		timePickerDialog.show()
	}

	private fun saveTime(selectedTime: Time) {
		selectedDate?.let { date ->
			val formattedTime = formatTime(selectedTime)
			timeRVAdapter.addTime(selectedTime, formattedTime)

			val key = getKey(date)
			sharedPreferences.edit().putString(key, formattedTime).apply()
		}
	}

	private fun initCalendarView() {
		binding.calendarView.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
			// 선택한 날짜 변경 시 기존에 선택한 날짜의 데이터를 초기화
			timeRVAdapter.clearTimeList()

			selectedDate = Calendar.getInstance().apply {
				set(date.year, date.month, date.day)
			}

			// 선택한 날짜에 대한 시간 데이터를 불러와서 RecyclerView 갱신
			loadTimeData(selectedDate!!)
		})
	}

	private fun loadTimeData(selectedDate: Calendar) {
		val key = getKey(selectedDate)
		val formattedTime = sharedPreferences.getString(key, null)

		formattedTime?.let {
			// 저장된 시간이 있다면 RecyclerView에 추가
			val selectedTime = parseFormattedTime(it)
			timeRVAdapter.addTime(selectedTime, formattedTime)
		}
	}

	private fun formatTime(time: Time): String {
		val calendar = Calendar.getInstance()
		calendar.set(Calendar.HOUR_OF_DAY, time.hour.toInt())
		calendar.set(Calendar.MINUTE, time.min.toInt())

		val simpleDateFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
		return simpleDateFormat.format(calendar.time)
	}

	private fun parseFormattedTime(formattedTime: String): Time {
		val calendar = Calendar.getInstance()
		val simpleDateFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
		calendar.time = simpleDateFormat.parse(formattedTime)!!

		return Time(
			hour = calendar.get(Calendar.HOUR_OF_DAY).toString(),
			min = calendar.get(Calendar.MINUTE).toString()
		)
	}

	private fun getKey(calendar: Calendar): String {
		return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
	}
}
