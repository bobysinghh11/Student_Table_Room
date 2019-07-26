package com.stuffshuf.student_table_room


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Long?= null,
    val fName:String,
    val lName:String,
    val email:String,
    val number:String
//    val male:String,
//    val femal:String

)