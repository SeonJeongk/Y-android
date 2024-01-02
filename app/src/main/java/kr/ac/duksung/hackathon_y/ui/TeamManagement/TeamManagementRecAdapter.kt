package kr.ac.duksung.hackathon_y.ui.TeamManagement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.databinding.ItemTeamBinding
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.ProgressDto

interface OnItemClickListener {
    fun onItemClick(position: Int, progressDto: ProgressDto)
}


class TeamManagementRecAdapter(
    private var progressList: List<ProgressDto>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<TeamManagementRecAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemTeamBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(progressDto: ProgressDto) {
            binding.apply {
                title.text = progressDto.title
                totalTask.text = progressDto.totaltask?.toString()
                finishedTask.text = progressDto.finishedtask?.toString()
                role.text=progressDto.role?.toString()

                progressBar.max = progressDto.totaltask ?: 0
                progressBar.progress = progressDto.finishedtask ?: 0

                // 아이템 클릭 이벤트 설정

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTeamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(progressList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(position, progressList[position])
        }
    }
    fun updateList(newList: List<ProgressDto>) {
        progressList = newList
        notifyDataSetChanged()
    }
    override fun getItemCount() = progressList.size
}
