package com.sciflare.task.roomdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras


class RoomFactory(private val repository:RoomRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return RoomViewModel(repository) as T
    }
}