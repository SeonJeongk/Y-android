package kr.ac.duksung.hackathon_y.ui.Project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.duksung.hackathon_y.R
import kr.ac.duksung.hackathon_y.databinding.FragmentAddProjectBinding
import kr.ac.duksung.hackathon_y.ui.TeamManagement.TeamRecAdapter


class AddProjectFragment : Fragment() {

    private var _binding: FragmentAddProjectBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProjectViewModel
    private lateinit var memberAdapter: MemberRecAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProjectBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)

        // MemberRecAdapter를 초기화합니다.
        memberAdapter = MemberRecAdapter(listOf())

        // RecyclerView에 Adapter를 설정합니다.
        binding.addmemRec.adapter = memberAdapter

        // RecyclerView의 레이아웃 매니저를 설정합니다.
        binding.addmemRec.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)


        viewModel.MemberDtos.observe(viewLifecycleOwner,{
                memberList->
            memberAdapter.updateList(memberList)
        })
        return binding.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
