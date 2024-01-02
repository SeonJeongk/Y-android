package kr.ac.duksung.hackathon_y.ui.Project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.duksung.hackathon_y.R
import kr.ac.duksung.hackathon_y.databinding.FragmentProjectBinding
import kr.ac.duksung.hackathon_y.ui.Project.dto.ProgressDto
import kr.ac.duksung.hackathon_y.ui.Project.dto.TeamDto
import kr.ac.duksung.hackathon_y.ui.TeamManagement.OnItemClickListener
import kr.ac.duksung.hackathon_y.ui.TeamManagement.TeamManagementViewModel
import kr.ac.duksung.hackathon_y.ui.TeamManagement.TeamRecAdapter

class ProjectFragment : Fragment() {
    private lateinit var progressAdapter: ProjectProgressRecAdapter
    private lateinit var teamAdapter: TeamRecAdapter
    private lateinit var viewModel: ProjectViewModel
    private var _binding: FragmentProjectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProjectBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(ProjectViewModel::class.java)

        // Initialize the adapters
        progressAdapter = ProjectProgressRecAdapter(listOf())
        teamAdapter = TeamRecAdapter(listOf())


        // Set the adapters to the RecyclerViews
        binding.progressRec.adapter = progressAdapter
        binding.progressRec.layoutManager = LinearLayoutManager(context)

        binding.teamRec.adapter = teamAdapter
        binding.teamRec.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.ProgressDtos.observe(viewLifecycleOwner, { progressList ->
            // 리스트가 변경될 때마다 adapter에 새 데이터를 제공
            progressAdapter.updateList(progressList)
        })

        viewModel.TeamDtos.observe(viewLifecycleOwner, { teamList ->
            teamAdapter.updateList(teamList)
        })

        binding.prjectBtn.setOnClickListener {
            val addProjectFragment = AddProjectFragment()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, addProjectFragment)
                addToBackStack(null)
                commit()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
