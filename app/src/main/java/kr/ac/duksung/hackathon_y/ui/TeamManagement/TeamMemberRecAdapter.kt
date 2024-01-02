package kr.ac.duksung.hackathon_y.ui.TeamManagement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.R
import kr.ac.duksung.hackathon_y.databinding.ItemTeammemberBinding
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.TeamMemberDto

class TeamMemberRecAdapter(private var teammemberList:List<TeamMemberDto>):RecyclerView.Adapter<TeamMemberRecAdapter.TeamMemberViewHolder>(){


    // ViewHolder 클래스 정의
    class TeamMemberViewHolder(private val binding: ItemTeammemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teammember: TeamMemberDto) {
            binding.name.text = teammember.title
            // 이미지 설정
            if (teammember.imageResId != null) {
                binding.profile.setImageResource(teammember.imageResId!!)
            } else {
                // 기본 이미지나 플레이스홀더 이미지 설정
                binding.profile.setImageResource(R.drawable.profile_image01)
            }
            val result = 100*((teammember.totaltask?.toDouble() ?: 0.0) / teammember.finishedtask!!)
            binding.percent.text = "%.0f".format(result)

            binding.progressBar.max = 100 // 프로그레스바의 최대값을 100으로 설정
            binding.progressBar.progress = ((teammember.finishedtask?.toDouble() ?: 0.0) / teammember.totaltask!! * 100).toInt() // 프로그레스바의 현재 상태를 계산하여 설정
        }
    }

    // onCreateViewHolder: 뷰홀더가 생성될 때 실행되는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMemberViewHolder {
        val binding = ItemTeammemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamMemberViewHolder(binding)
    }

    // getItemCount: 아이템의 개수를 반환하는 함수
    override fun getItemCount(): Int {
        return teammemberList.size
    }

    // onBindViewHolder: 뷰홀더가 재사용될 때 실행되는 함수
    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        val team = teammemberList[position]
        holder.bind(team)
    }

    fun updateList(newList: List<TeamMemberDto>) {
        teammemberList = newList
        notifyDataSetChanged()
    }
}