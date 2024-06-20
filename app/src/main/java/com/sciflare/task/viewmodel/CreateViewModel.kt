package com.sciflare.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.sciflare.task.model.SaveData
import com.sciflare.task.repo.CreateRepo
import com.sciflare.task.roomdb.RoomDao
import com.sciflare.task.roomdb.RoomRepo
import kotlinx.coroutines.launch


/**
 * its Transforming the data from the model
 * its is provide data streaming to view and also callback to update to view
 * viewmodel is part of android architecture component used to Separate  business logic from ui logic
 * hold the data from the data class and store and manage ui related data android life cycle conscious way


 */

class CreateViewModel : ViewModel() {
    private val createRepo by lazy { CreateRepo() }

    /**
     * it will not invoke
     * its Obsorve callback unless activity or fragment is receive OnStart Method
     *
     */
    val mutableLiveDataResponse by lazy { createRepo.mutableLiveData }
    suspend fun sendDetails(jsonObject: JsonObject) {
        createRepo.sendDetails(jsonObject)
    }

    val getMutableLiveData by lazy { createRepo.getDataMutableLiveData }

    suspend fun getDetails() {
        createRepo.getData()
    }


    private val roomRepo: RoomRepo? = null
    fun insert(saveData: SaveData) = viewModelScope.launch {
        roomRepo?.insert(saveData)
    }

    fun getList() = roomRepo?.getList()

}