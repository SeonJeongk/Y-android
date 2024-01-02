package kr.ac.duksung.hackathon_y.ui.TeamManagement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.R
import kr.ac.duksung.hackathon_y.databinding.ItemTeamsBinding
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.ProgressDto
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.TeamDto

class TeamRecAdapter(private var teamList: List<TeamDto>) : RecyclerView.Adapter<TeamRecAdapter.TeamViewHolder>() {

    // ViewHolder 클래스 정의
    class TeamViewHolder(private val binding: ItemTeamsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team: TeamDto) {
            binding.name.text = team.name
        }
    }

    // onCreateViewHolder: 뷰홀더가 생성될 때 실행되는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ItemTeamsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    // getItemCount: 아이템의 개수를 반환하는 함수
    override fun getItemCount(): Int {
        return teamList.size
    }

    // onBindViewHolder: 뷰홀더가 재사용될 때 실행되는 함수
    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teamList[position]
        holder.bind(team)
    }

    fun updateList(newList: List<TeamDto>) {
        teamList = newList
        notifyDataSetChanged()
    }
}
