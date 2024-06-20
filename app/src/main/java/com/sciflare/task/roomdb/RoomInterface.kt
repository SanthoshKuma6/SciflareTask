package com.sciflare.task.roomdb


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sciflare.task.model.SaveData
import kotlinx.coroutines.flow.Flow


@Dao
interface RoomInterface {

    @Insert
    suspend fun insert(move:SaveData)

    @Query("select * from myData")
    fun getList(): Flow<List<SaveData>>
}