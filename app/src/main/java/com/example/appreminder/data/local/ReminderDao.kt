package com.example.appreminder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appreminder.data.model.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item:Reminder)

    @Query("SELECT * FROM tbl_Reminder WHERE id=:idm")
    fun get(idm: Int): Flow<Reminder>

    @Query("SELECT * FROM tbl_Reminder")
    fun getAll(): Flow<List<Reminder>>

    @Query("DELETE FROM tbl_Reminder")
    fun deleteAll()

    @Query("DELETE FROM tbl_Reminder WHERE id= :idm")
    fun delete(idm: Int)

}