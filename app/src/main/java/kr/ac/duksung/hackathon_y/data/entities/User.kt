package kr.ac.duksung.hackathon_y.data.entities

import androidx.room.PrimaryKey

data class User(
    var studentId: String,
    var password: String
){
    @PrimaryKey(autoGenerate = false) var id : Int = 0
}
