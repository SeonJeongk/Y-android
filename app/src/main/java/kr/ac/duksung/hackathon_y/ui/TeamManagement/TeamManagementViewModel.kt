package kr.ac.duksung.hackathon_y.ui.TeamManagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.ac.duksung.hackathon_y.R
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.ProgressDto
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.TeamDto
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.TeamMemberDto

class TeamManagementViewModel: ViewModel() {
    private val _ProgressDtos = MutableLiveData<List<ProgressDto>>()
    val ProgressDtos: MutableLiveData<List<ProgressDto>> get() = _ProgressDtos

    private val  _TeamDtos=MutableLiveData<List<TeamDto>>()
    val TeamDtos:MutableLiveData<List<TeamDto>>get()=_TeamDtos

    private val _TeamMemberDtos=MutableLiveData<List<TeamMemberDto>>()
    val TeamMemberDtos:MutableLiveData<List<TeamMemberDto>> get()=_TeamMemberDtos
    init {
        setProgressDto()
        setTeamDto()
        setTeamMemberDto()
    }

    // Set Maindto list
    private fun setProgressDto() {
        _ProgressDtos.value = listOf(

            ProgressDto("엄격한 관리자팀⚡",33,30,"Design" ),
            ProgressDto("엄격한 관리자팀⚡",99,33,"Android" ),
            ProgressDto("엄격한 관리자팀⚡",51,12,"Server" )


        )
    }

    private fun setTeamDto(){
        _TeamDtos.value= listOf(
            TeamDto("엄격한 관리자팀⚡"),
            TeamDto("UMC5thHack✨"),
            TeamDto("ESTJ😏"),
            TeamDto("컴구스터디🫠")
        )
    }

    private fun setTeamMemberDto(){
        _TeamMemberDtos.value= listOf(
            TeamMemberDto("에릭",30,33 ),
            TeamMemberDto("리나",3,33,R.drawable.profile_image02)
        )

    }
}