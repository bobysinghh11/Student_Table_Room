package com.stuffshuf.student_table_room


interface ListItemClickList {

    fun checkBoxListener(todo: Todo, position: Int)
    fun delButtonListener(todo: Todo, position: Int)
}