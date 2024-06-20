package com.sciflare.task.roomdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sciflare.task.model.SaveData
import kotlinx.coroutines.launch

class RoomViewModel(private val roomRepo : RoomRepo) : ViewModel() {
    fun insert(saveData: SaveData) = viewModelScope.launch {
        roomRepo.insert(saveData)
    }
    fun getList() = roomRepo.getList()

}