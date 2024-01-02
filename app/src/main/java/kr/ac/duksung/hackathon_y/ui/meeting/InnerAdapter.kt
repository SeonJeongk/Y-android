package kr.ac.duksung.hackathon_y.ui.meeting

//import CheckActivity
//import CheckActivity
import android.content.Intent
import android.graphics.Typeface
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kr.ac.duksung.hackathon_y.CheckActivity
import kr.ac.duksung.hackathon_y.R
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
        private val btnAdd: ImageButton = itemBinding.imgBtn
        private val container: LinearLayout = itemBinding.container

        fun bind(innerItem: InnerItem) {
            btnAdd.setOnClickListener {
                val linearLayout = LinearLayout(itemView.context)
                linearLayout.orientation = LinearLayout.HORIZONTAL

                val editText = EditText(itemView.context)
                editText.hint = "# 파트 추가하기"
                editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                editText.setTypeface(null, Typeface.BOLD)

                val button = Button(itemView.context)
                button.text = "할일 추가"
//                val backgroundColor = ContextCompat.getColor(itemView.context, R.color.mainColor)
//                button.setBackgroundColor(backgroundColor)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                // Set margin to the views
                layoutParams.setMargins(16, 0, 0, 0)

                editText.layoutParams = layoutParams
                button.layoutParams = layoutParams

                // Add the views to the LinearLayout
                linearLayout.addView(editText)
                linearLayout.addView(button)

                // Add the LinearLayout to the main container
                container.addView(linearLayout)

                button.setOnClickListener {
                    val intent = Intent(itemView.context, CheckActivity::class.java)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}
