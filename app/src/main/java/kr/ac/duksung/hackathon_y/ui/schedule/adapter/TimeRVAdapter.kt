package kr.ac.duksung.hackathon_y.ui.schedule.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.databinding.ItemTimeBinding
import kr.ac.duksung.hackathon_y.ui.schedule.Time

class TimeRVAdapter(private val context: Context, private val sharedPreferences: SharedPreferences) : RecyclerView.Adapter<TimeRVAdapter.TimeHolder>() {
	var time: MutableList<Time> = mutableListOf()
	var formattedTime: MutableList<String> = mutableListOf()

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

	fun removeTime(position: Int) {
		time.removeAt(position)
		formattedTime.removeAt(position)
		notifyDataSetChanged()

		saveTimeDataToSharedPreferences()
	}

	private fun saveTimeDataToSharedPreferences() {
		val editor = sharedPreferences.edit()
		editor.clear()

		for (i in 0 until time.size) {
			val key = getKeyForPosition(i)
			val formattedTime = formattedTime[i]
			editor.putString(key, formattedTime)
		}

		editor.apply()
	}

	private fun getKeyForPosition(position: Int): String {
		return "time_$position"
	}

	private fun saveTimeData() {
		val editor = sharedPreferences.edit()
		editor.clear()

		for (i in 0 until time.size) {
			val key = getKeyForPosition(i)
			val formattedTime = formatTime(time[i])
			editor.putString(key, formattedTime)
		}

		editor.apply()
	}

	private fun loadTimeData() {
		val keys = sharedPreferences.all.keys

		time.clear()
		formattedTime.clear()

		for (key in keys) {
			if (key.startsWith("time_")) {
				val formattedTime = sharedPreferences.getString(key, "")
				if (formattedTime != null) {
					if (formattedTime.isNotEmpty()) {
						val timeArray = formattedTime.split(":")
						if (timeArray.size == 2) {
							val hour = timeArray[0]
							val min = timeArray[1]
							time.add(Time(hour, min))
							this.formattedTime.add(formattedTime!!)
						}
					}
				}
			}
		}

		notifyDataSetChanged()
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
		return "${time.hour}:${time.min}"
	}

	init {
		loadTimeData()
	}
}
