package com.anandaiqbal.oop2.Database


import androidx.room.*

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun addMahasiswa(mahasiswa: Mahasiswa)

    @Update
    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)

    @Delete
    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)

    @Query("SELECT * FROM mahasiswa")
    suspend fun getAllMahasiswa(): List<Mahasiswa>

    @Query("SELECT * FROM mahasiswa WHERE id=:mahasiswa_id")
    suspend fun getMahasiswa(mahasiswa_id: Int) : List<Mahasiswa>
}