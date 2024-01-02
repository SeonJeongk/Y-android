
package kr.ac.duksung.hackathon_y.ui.schedule.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.ui.schedule.Date
/*
class DateRVAdapter: RecyclerView.Adapter<DateRVAdapter.SearchHolder>() {
	var date: List<Date>? = null

	override fun getItemCount(): Int {
		return date?.size ?: 0
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
		val itemBinding =
			ItemCalendarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return SearchHolder(itemBinding)
	}

	override fun onBindViewHolder(holder: SearchHolder, position: Int) {
		holder.itemBinding.tvDay.text = date?.get(position).toString()
		holder.itemBinding.tvDate.text = date?.get(position).toString()

//		holder.itemBinding.tvSearchAddress.setOnClickListener {
//			clickListener?.onItemClick(it, position)
//		}
	}

	class SearchHolder(val itemBinding: ItemCalendarBinding) : RecyclerView.ViewHolder(itemBinding.root)

	interface OnItemClickListner {
		fun onItemClick(view: View, position: Int)
	}

	var clickListener: OnItemClickListner? = null

	fun setOnItemClickListener(listener: OnItemClickListner) {
		this.clickListener = listener
	}
	inner class ViewHolder(val binding: ItemCalendarBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(date: Date) {
			binding.tvDate.text = date.date.toString()
			binding.tvDay.text = date.day
		}
	}
}*/
