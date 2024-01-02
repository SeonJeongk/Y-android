package kr.ac.duksung.hackathon_y.ui.Project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.databinding.ItemMemberBinding
import kr.ac.duksung.hackathon_y.ui.Project.dto.MemberDto

class MemberRecAdapter(
    private var memberList: List<MemberDto>
) : RecyclerView.Adapter<MemberRecAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemMemberBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(memberDto: MemberDto) {
            binding.apply {
                // imageResId가 null이 아닐 때만 이미지를 설정합니다.
                memberDto.imageResId?.let { imageResId ->
                    binding.image.setImageResource(imageResId)
                }
                memberDto.name?.let{
                    it->binding.name.text=it
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(memberList[position])
    }

    fun updateList(newList: List<MemberDto>) {
        memberList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = memberList.size
}
