package com.example.breakingbad.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class BBCharacter(

    @PrimaryKey
    var id: Int,
    var name: String,
    var birthday: String,
    var occupation: Array<String>,
    var img: String?,
    var status: String,
    var nickname: String


)