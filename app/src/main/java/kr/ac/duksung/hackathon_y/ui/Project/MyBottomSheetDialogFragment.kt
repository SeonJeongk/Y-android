package kr.ac.duksung.hackathon_y.ui.Project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.ac.duksung.hackathon_y.R
import kr.ac.duksung.hackathon_y.ui.Project.dto.MemberDto

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: ProjectViewModel
    private lateinit var adapter: AddMemberRecAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_bottom_sheet_dialog, container, false)

        // ViewModel 초기화
        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)

        // Adapter 초기화

        adapter = AddMemberRecAdapter(listOf(MemberDto(R.drawable.profile_image01,"에릭","Team"),MemberDto(R.drawable.profile_image02,"샐리","Member")))

        // RecyclerView에 Adapter 설정
        val recyclerView: RecyclerView = view.findViewById(R.id.rec)
        recyclerView.adapter = adapter



        return view
    }
    override fun onStart() {
        super.onStart()

        val bottomSheet = (dialog as? BottomSheetDialog)?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let {
            it.post {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.peekHeight = ((context?.resources?.displayMetrics?.heightPixels ?: 0) * 0.5).toInt()
            }
        }
    }


}
