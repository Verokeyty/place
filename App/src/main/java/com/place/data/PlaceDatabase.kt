
package com.place.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.place.model.Place
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities=[Place::class], version = 1, exportSchema = false)

abstract class PlaceDatabase : RoomDatabase(){
    abstract fun placeDao() : PlaceDao

    companion object {

        @Volatile
        private var INSTANCE: PlaceDatabase? = null

//set


        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: android.content.Context): PlaceDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaceDatabase :: class.java,
                    "place_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
