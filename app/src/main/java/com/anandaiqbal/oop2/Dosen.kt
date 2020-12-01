package com.satria.oop

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dosen")
data class Dosen(
    @PrimaryKey val nipy: Int,
    @ColumnInfo(name = "nama") val nama: String?,
    @ColumnInfo(name = "pengampuh") val prodi: String?
)