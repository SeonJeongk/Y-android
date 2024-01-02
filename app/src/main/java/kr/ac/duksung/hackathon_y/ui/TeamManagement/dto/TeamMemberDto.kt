package kr.ac.duksung.hackathon_y.ui.TeamManagement.dto

data class TeamMemberDto(
    var title: String? = null,
    var totaltask: Int? = null,
    var finishedtask: Int? = null,
    var imageResId: Int? = null // 이미지 URL을 저장할 속성 추가
)

