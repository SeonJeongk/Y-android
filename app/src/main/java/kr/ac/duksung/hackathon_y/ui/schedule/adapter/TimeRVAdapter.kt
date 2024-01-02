package kr.ac.duksung.hackathon_y.ui.schedule.adapter

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import kr.ac.duksung.hackathon_y.databinding.ItemTimeBinding
import kr.ac.duksung.hackathon_y.ui.schedule.Time
import java.util.Calendar

class TimeRVAdapter(private val context: Context, private val sharedPreferences: SharedPreferences) : RecyclerView.Adapter<TimeRVAdapter.TimeHolder>() {
	var time: MutableList<Time> = mutableListOf()
	var formattedTime: MutableList<String> = mutableListOf()
	var selectedDate: Calendar? = null

	override fun getItemCount(): Int {
		return time.size
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeHolder {
		val itemBinding =
			ItemTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return TimeHolder(itemBinding)
	}

	override fun onBindViewHolder(holder: TimeHolder, position: Int) {

		holder.itemBinding.tvTimeContent.text = formattedTime[position]

		holder.itemBinding.btnDelete.setOnClickListener {
			removeTime(position)
		}
	}

	fun addTime(newTime: Time, formattedTime: String) {
		this.time.add(newTime)
		this.formattedTime.add(formattedTime)
		saveTimeData()
		notifyDataSetChanged()
	}

	fun clearTimeList() {
		time.clear()
		formattedTime.clear()
		notifyDataSetChanged()
	}

	private fun removeTime(position: Int) {
		time.removeAt(position)
		formattedTime.removeAt(position)

		val editor = sharedPreferences.edit()
		val key = getKeyForPosition(position)
		editor.remove(key)
		editor.apply()

		notifyDataSetChanged()
	}

	private fun getKeyForPosition(position: Int): String {
		return "time_$position"
	}

	private fun saveTimeData() {
		val editor = sharedPreferences.edit()
		val timeSet = HashSet<String>()

		for (t in time) {
			timeSet.add(t.hour.toString() + ":" + t.min.toString())
		}

		editor.putStringSet("timeSet", timeSet)
		editor.apply()
	}

	class TimeHolder(val itemBinding: ItemTimeBinding) : RecyclerView.ViewHolder(itemBinding.root)

	interface OnItemClickListener {
		fun onItemClick(view: View, position: Int)
	}

	var itemClickListener: OnItemClickListener? = null

	fun setOnItemClickListener(listener: OnItemClickListener) {
		this.itemClickListener = listener
	}

	inner class ViewHolder(val binding: ItemTimeBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(time: Time, formattedTime: String) {
			binding.tvTimeContent.text = formattedTime
		}
	}

	private fun formatTime(time: Time): String {
		val formattedHour = String.format("%02d", time.hour)
		val formattedMin = String.format("%02d", time.min)
		return "$formattedHour:$formattedMin"
	}
}