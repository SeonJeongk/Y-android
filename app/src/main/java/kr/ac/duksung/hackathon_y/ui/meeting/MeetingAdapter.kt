package kr.ac.duksung.hackathon_y.ui.meeting

//import InnerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.data.entities.Meeting
import kr.ac.duksung.hackathon_y.databinding.ItemMeetingBinding

class MeetingAdapter(private val meetingList: List<Meeting>) : RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        val itemBinding = ItemMeetingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeetingViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        val meeting = meetingList[position]
        holder.bind(meeting)
    }

    override fun getItemCount(): Int {
        return meetingList.size
    }

    inner class MeetingViewHolder(private val itemBinding: ItemMeetingBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val build: TextView = itemBinding.tvBuild
        private val date: TextView = itemBinding.tvDate
        private val time: TextView = itemBinding.tvTime
        private val layoutExpand: ConstraintLayout = itemBinding.layoutDetail
        private val meetingDetailRecyclerView: RecyclerView = itemBinding.rvMeetingDetail

        fun bind(meeting: Meeting) {
            // 아이템의 데이터를 가져와서 뷰에 설정
            build.text = meeting.title
            date.text = meeting.date
            time.text = meeting.time

            // 아이템 확장 여부에 따라 확장된 레이아웃의 가시성 설정
            layoutExpand.visibility = if (meeting.isExpanded) View.VISIBLE else View.GONE

            // 아이템 클릭 시 확장 여부 토글
            itemView.setOnClickListener {
                meeting.isExpanded = !meeting.isExpanded
                notifyItemChanged(adapterPosition)
            }

            // 아이템이 확장된 경우 내부 RecyclerView 설정
            if (meeting.isExpanded) {
                // 내부 RecyclerView를 초기화하고 어댑터 설정
                val innerAdapter = InnerAdapter(meeting.innerList) // InnerAdapter는 내부 RecyclerView의 어댑터입니다.
                meetingDetailRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
                meetingDetailRecyclerView.adapter = innerAdapter
            }


        }

    }
}
