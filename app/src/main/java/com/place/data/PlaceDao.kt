package com.place.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.place.model.Place

@Dao

interface PlaceDao {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun addPlace (place: Place)

    @Update(onConflict= OnConflictStrategy.IGNORE)
    suspend fun updatePlace (place: Place)
    
    @Delete
    suspend fun deletePlace(place: Place)

    @Query ( "SELECT * FROM PLACE")
    fun getAllData() : LiveData<List<Place>>
}