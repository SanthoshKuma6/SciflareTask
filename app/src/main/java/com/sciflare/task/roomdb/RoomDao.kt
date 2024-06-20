package com.sciflare.task.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sciflare.task.model.SaveData



/**
An abstract class in Android is a class that cannot be instantiated on its own and is meant to be subclassed.
It can have both abstract methods (without implementation) and concrete methods (with implementation) to provide a common base and shared functionality for its subclasses.
 */
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