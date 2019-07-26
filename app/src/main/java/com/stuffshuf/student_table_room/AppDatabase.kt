package com.stuffshuf.student_table_room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Todo::class), version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}