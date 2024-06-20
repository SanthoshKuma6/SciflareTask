package com.sciflare.task.roomdb

import com.sciflare.task.model.SaveData

class RoomRepo(private val roomDao: RoomDao) {

    suspend fun insert(saveData: SaveData) = roomDao.roomInterface().insert(saveData)

    fun getList() = roomDao.roomInterface().getList()

}