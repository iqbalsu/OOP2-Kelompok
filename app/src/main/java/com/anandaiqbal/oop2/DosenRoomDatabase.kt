package com.satria.oop

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Mahasiswa::class), version = 1)
abstract class DosenRoomDatabase : RoomDatabase() {
    abstract fun dosenDao(): DosenDao

    abstract val applicationContext: Context
    val db = Room.databaseBuilder(
        applicationContext,
        DosenRoomDatabase::class.java, "DBMHS"
    ).build()

}