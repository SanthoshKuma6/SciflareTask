package com.sciflare.task.model

import androidx.room.Entity
import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "myData")
data class SaveData (
        var name:String?=null,
        var email:String?=null,
        var mobile:String?=null,
        var gender:String?=null,
        @PrimaryKey(autoGenerate = true)
        val id:Int=0
):Parcelable
