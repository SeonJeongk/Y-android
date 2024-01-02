package kr.ac.duksung.hackathon_y.ui.meeting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.duksung.hackathon_y.R
import kr.ac.duksung.hackathon_y.data.entities.InnerItem
import kr.ac.duksung.hackathon_y.data.entities.Meeting
import kr.ac.duksung.hackathon_y.databinding.FragmentMeetingBinding

class MeetingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewBinding: FragmentMeetingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewBinding = FragmentMeetingBinding.inflate(inflater, container,false)
        viewBinding.actionbar.appbarMeeting.visibility = View.VISIBLE
        viewBinding.btnWrite.setOnClickListener {
            val destinationFragment = WriteMeetingFragment()

            // 프래그먼트 트랜잭션 시작
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

            // 프래그먼트를 백 스택에 추가하고 트랜잭션 커밋
            transaction.replace(R.id.fragment_container, destinationFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        setupRecyclerView()

        viewBinding.btnWrite.setOnClickListener {
            showWriteMeetingDialog()
        }


        return viewBinding.root
    }
    private fun setupRecyclerView() {
        val meetingRecyclerView = viewBinding.rvMeeting

        val meetingList = mutableListOf<Meeting>()

        val innerItem1 = InnerItem("Agenda 1")
        val innerItem2 = InnerItem("Agenda 2")
        val innerItem3 = InnerItem("Agenda 3")

        val meeting1 = Meeting("Meeting 1", "2024-01-01", "10:00 AM", innerList = listOf(innerItem1, innerItem2))
        val meeting2 = Meeting("Meeting 2", "2024-01-02", "02:30 PM", innerList = listOf(innerItem3))

        // 미팅 리스트에 추가
        meetingList.add(meeting1)
        meetingList.add(meeting2)
        meetingRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        meetingRecyclerView.adapter = MeetingAdapter(meetingList)
    }

    private fun showWriteMeetingDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("새 회의록 작성하기")

        // Inflate the layout for the dialog
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog, null)

        // Customize the dialog view and add any necessary functionality
        builder.setView(dialogView)
            .setPositiveButton("새 회의록 추가하기") { dialog, _ ->
                // Handle OK button click if needed
                dialog.dismiss()
            }

        val dialog = builder.create()

        dialog.show()
    }






}