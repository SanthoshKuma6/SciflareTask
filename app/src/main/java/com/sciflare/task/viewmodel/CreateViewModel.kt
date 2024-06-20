package com.sciflare.task.viewmodel

import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.sciflare.task.repo.CreateRepo

class CreateViewModel : ViewModel() {
    private val createRepo by lazy { CreateRepo() }
    val mutableLiveDataResponse by lazy { createRepo.mutableLiveData }
   suspend  fun sendDetails(jsonObject: JsonObject) {
            createRepo.sendDetails(jsonObject)
    }

    val getMutableLiveData by lazy { createRepo.getDataMutableLiveData }

    suspend fun getDetails(){
        createRepo.getData()
    }

}