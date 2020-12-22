package com.anandaiqbal.oop2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Mahasiswa::class, Dosen::class), version = 1)

abstract class RoomDB : RoomDatabase() {

    abstract fun mahasiswaDao(): MahasiswaDao
    abstract fun dosenDao(): DosenDao

    companion object {

        @Volatile
        private var INSTANCE: RoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "APPDB"
        ).build()

    }
}