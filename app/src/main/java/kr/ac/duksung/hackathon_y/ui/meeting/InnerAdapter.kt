package kr.ac.duksung.hackathon_y.ui.meeting

import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.data.entities.InnerItem
import kr.ac.duksung.hackathon_y.databinding.ItemInnerBinding

class InnerAdapter(private val innerList: List<InnerItem>) : RecyclerView.Adapter<InnerAdapter.InnerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerViewHolder {
        val itemBinding = ItemInnerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InnerViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: InnerViewHolder, position: Int) {
        val innerItem = innerList[position]
        holder.bind(innerItem)
    }

    override fun getItemCount(): Int {
        return innerList.size
    }

    inner class InnerViewHolder(private val itemBinding: ItemInnerBinding) : RecyclerView.ViewHolder(itemBinding.root) {
//        private val innerTextView: TextView = itemBinding.tvPart
        private val btnAdd: ImageButton = itemBinding.imgBtn
        private val container: LinearLayout = itemBinding.container
        fun bind(innerItem: InnerItem) {
            // 내부 아이템의 데이터를 가져와서 뷰에 설정
//            innerTextView.text = innerItem.part

            btnAdd.setOnClickListener {
                val text = TextView(itemView.context)
                val editText = EditText(itemView.context)
                editText.hint = "# 파트 추가하기"

                editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f) // 원하는 크기로 설정

// 글씨 스타일 설정
                editText.setTypeface(null, Typeface.BOLD)

                val layoutParams = LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.WRAP_CONTENT)
                editText.layoutParams = layoutParams

                // 동적으로 추가된 EditText를 LinearLayout에 추가
                container.addView(editText)
            }
        }
    }
}
