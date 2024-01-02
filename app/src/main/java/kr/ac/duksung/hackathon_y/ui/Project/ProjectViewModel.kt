package kr.ac.duksung.hackathon_y.ui.Project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.ProgressDto
import kr.ac.duksung.hackathon_y.ui.TeamManagement.dto.TeamDto

class ProjectViewModel:ViewModel() {

    private val _ProgressDtos = MutableLiveData<List<ProgressDto>>()
    val ProgressDtos: MutableLiveData<List<ProgressDto>> get() = _ProgressDtos

    private val  _TeamDtos=MutableLiveData<List<TeamDto>>()
    val TeamDtos:MutableLiveData<List<TeamDto>>get()=_TeamDtos
    init {
        setProgressDto()
        setTeamDto()
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
}