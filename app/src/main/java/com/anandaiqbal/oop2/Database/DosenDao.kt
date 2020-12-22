package com.anandaiqbal.oop2.Database


import androidx.room.*

@Dao
interface DosenDao {
    @Insert
    suspend fun addDosen(dosen: Dosen)

    @Update
    suspend fun updateDosen(dosen: Dosen)

    @Delete
    suspend fun deleteDosen(dosen: Dosen)

    @Query("SELECT * FROM dosen")
    suspend fun getAllDosen(): List<Dosen>

    @Query("SELECT * FROM dosen WHERE id=:dosen_id")
    suspend fun getDosen(dosen_id: Int) : List<Dosen>
}