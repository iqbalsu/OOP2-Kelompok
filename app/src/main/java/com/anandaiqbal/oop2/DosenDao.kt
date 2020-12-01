package com.satria.oop

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DosenDao {
    @Query("SELECT * FROM dosen")
    fun getAll(): List<Dosen>

    @Query("SELECT * FROM dosen WHERE nipy IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Dosen>

    @Query("SELECT * FROM dosen WHERE nama LIKE :nama AND " +
            "pengampuh LIKE :pengampuh LIMIT 1")
    fun findByName(nama: String, pengampuh: String): Dosen

    @Insert
    fun insertAll(vararg dosen: Dosen)

    @Delete
    fun delete(dosen: Dosen)
}