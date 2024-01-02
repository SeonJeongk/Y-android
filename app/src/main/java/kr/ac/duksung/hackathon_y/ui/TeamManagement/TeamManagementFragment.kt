package kr.ac.duksung.hackathon_y.ui.TeamManagement

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.duksung.hackathon_y.R
import kr.ac.duksung.hackathon_y.databinding.FragmentTeamManagementBinding
import kr.ac.duksung.hackathon_y.ui.Project.AddProjectFragment
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.ProgressDto
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.TeamMemberDto

class TeamManagementFragment : Fragment() {
    private var _binding: FragmentTeamManagementBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TeamManagementViewModel // ViewModel 초기화
    private lateinit var progressAdapter: TeamManagementRecAdapter
    private var isExpanded = false // 추가된 변수
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamManagementBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TeamManagementViewModel::class.java) // ViewModel 초기화

	    binding.actionbar.tvTitle.visibility = View.VISIBLE
	    binding.actionbar.tvTitle.text = "팀원 관리"

        // Fragment에서 클릭 이벤트 처리
        progressAdapter = TeamManagementRecAdapter(listOf(), object: OnItemClickListener {
            override fun onItemClick(position: Int, progressDto: ProgressDto) {
                if (!isExpanded) {
                    // 클릭 시 teammember_rec의 Visibility를 변경
                    binding.teammemberRec.visibility = View.VISIBLE

                    // 클릭된 아이템만을 보여주기 위해 progressrec의 데이터를 업데이트
                    progressAdapter.updateList(listOf(progressDto))

                    // teammember_rec에 데이터를 설정하는 로직 추가
                    val teamMemberAdapter = TeamMemberRecAdapter(listOf(
                        TeamMemberDto("에릭",33,33),
                        TeamMemberDto("리나",3,33,R.drawable.profile_image02)

                    ))
                    binding.teammemberRec.adapter = teamMemberAdapter
                } else {
                    // 원래 상태로 돌아가야 하는 경우 코드를 추가합니다.
                    // 예를 들어, 모든 데이터를 다시 보여주게 하려면 아래와 같이 작성합니다.
                    progressAdapter.updateList(viewModel.ProgressDtos.value ?: listOf())
                    binding.teammemberRec.visibility = View.GONE
                }

                // 상태를 토글합니다.
                isExpanded = !isExpanded
            }
        })

        binding.prjectBtn.setOnClickListener {
            val addProjectFragment = AddProjectFragment()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, addProjectFragment)
                addToBackStack(null)
                commit()
            }
        }
        binding.progressRec.adapter = progressAdapter
        binding.progressRec.layoutManager = LinearLayoutManager(context)

        val teamAdapter = TeamRecAdapter(listOf())
        binding.teamRec.adapter = teamAdapter
        binding.teamRec.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val teammemberAdapter=TeamMemberRecAdapter(listOf())
        binding.teammemberRec.adapter=teammemberAdapter
        binding.teammemberRec.layoutManager=LinearLayoutManager(context)
        // 팀 리스트를 가져오는 함수
        viewModel.ProgressDtos.observe(viewLifecycleOwner, { progressList ->
            // 리스트가 변경될 때마다 adapter에 새 데이터를 제공
            progressAdapter.updateList(progressList)
        })

        viewModel.TeamDtos.observe(viewLifecycleOwner,{
                teamList ->
            teamAdapter.updateList(teamList)
        })

        viewModel.TeamMemberDtos.observe(viewLifecycleOwner,{
            teammemberList->
            teammemberAdapter.updateList(teammemberList)
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

