package kr.ac.duksung.hackathon_y.ui.TeamManagement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.databinding.ItemTeammemberBinding
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.TeamMemberDto

class TeamMemberRecAdapter(private var teammemberList:List<TeamMemberDto>):RecyclerView.Adapter<TeamMemberRecAdapter.TeamMemberViewHolder>(){


    // ViewHolder 클래스 정의
    class TeamMemberViewHolder(private val binding: ItemTeammemberBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teammember: TeamMemberDto) {
            binding.name.text = teammember.title

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