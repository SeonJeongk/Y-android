package kr.ac.duksung.hackathon_y.ui.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.duksung.hackathon_y.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {
	private lateinit var binding: FragmentScheduleBinding

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = FragmentScheduleBinding.inflate(inflater, container, false)

//		initRecyclerView()



		return binding.root
	}

	/*
	private fun initRecyclerView() {
		val itemList = ArrayList<Date>()

		itemList.add(Date(2, "Tue"))
		itemList.add(Date(3, "Wed"))
		itemList.add(Date(4, "Thu"))
		itemList.add(Date(5, "Fri"))
		itemList.add(Date(6, "Sat"))
		itemList.add(Date(7, "Sun"))
		itemList.add(Date(8, "Mon"))

		val dateRVAdapter = DateRVAdapter()

		Log.d("Schedule", "어댑터 불러오기")

		binding.recyclerReview.adapter = dateRVAdapter
		binding.recyclerReview.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
	} */
}

