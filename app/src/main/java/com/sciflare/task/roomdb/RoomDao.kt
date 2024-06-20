package com.sciflare.task.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sciflare.task.model.SaveData


@Database(
    entities = [SaveData::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDao : RoomDatabase() {
    abstract fun roomInterface(): RoomInterface

    companion object {
        private var Instant: RoomDao? = null
        fun getDatabase(context: Context): RoomDao {
            return Instant ?: synchronized(this) {
                val instant = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDao::class.java,
                    "Data Base"
                ).fallbackToDestructiveMigration()
                    .build()
                Instant = instant
                instant
            }
        }
    }
}