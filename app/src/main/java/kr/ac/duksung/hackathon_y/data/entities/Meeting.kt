package kr.ac.duksung.hackathon_y.data.entities
data class InnerItem(
    var part: String // 실제 사용하는 내부 아이템의 데이터 모델로 수정
)

data class Meeting(
    var title: String,
    var date: String,
    var time: String,
    var innerList: List<InnerItem>,
    var isExpanded: Boolean = false,

)