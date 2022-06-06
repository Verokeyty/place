package com.place.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.place.model.Place

@Database(entities=[Place::class], version = 1, exportSchema = false)

abstract class PlaceDataBase : RoomDatabase(){
    abstract fun placeDao() : PlaceDao

    companion object {

        @volatile
        private var INSTANCE: PlaceDataBase = null

        fun getDataBase(context: android.content.Context): PlaceDataBase{
            val tempInstance = INSTANCE
            val (tempInstance != null){
                return tempInstance
            }
            synchronized(this)
            val instance = Room.databaseBuilder(
                context.aplicationContext,
                PlaceDataBase:: class.java
            "place_database"
            ).build()
            INSTACE = instance
            return instance
    }
    }
}
}