package kr.ac.duksung.hackathon_y.ui.alarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.databinding.ItemAlarmBinding

class AlarmRVAdapter() : RecyclerView.Adapter<AlarmRVAdapter.ViewHolder>() {

    // 더미 데이터
    val part = arrayOf( "프론트", "백앤드", "디자인", "디자인", "백엔드", "PM" )
    val contents = arrayOf(
        "로그인 ui 구현",
        "로그인 API 완성",
        "회원가입 기능 디자인 완성",
        "로그인 기능 디자인 완성",
        "ERD 완성",
        "스토리보드 완성"
    )
    val deadline = arrayOf("01/03", "01/02", "01/02", "01/02", "01/01", "12/31")
    val postDate = arrayOf("2024.01.02", "2024.01.01", "2024.01.01", "2024.01.01", "2023.12.31", "2023.12.30")

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAlarmBinding = ItemAlarmBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false)

        return ViewHolder(binding)  //item view 객체 return
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)    // viewHolder로 item view 객체 넘겨준다
    }

    override fun getItemCount(): Int = contents.size

    //item view 객체를 담고 있는 viewHolder
    inner class ViewHolder(val binding: ItemAlarmBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.tvPart.text = part[position]    // 파트명
            binding.tvContents.text = contents[position]    // 일정 내용
            binding.tvDeadlineDate.text = deadline[position]    // 마감일
            binding.tvAlarmDate.text = postDate[position]   // 알림 발송일
        }
    }
}