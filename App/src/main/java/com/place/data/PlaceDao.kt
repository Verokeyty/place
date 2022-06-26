package com.place.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.place.model.Place

@Dao

interface PlaceDao {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
     fun addPlace (place: Place)

    @Update(onConflict= OnConflictStrategy.IGNORE)
    fun updatePlace (place: Place)
    
    @Delete
   fun deletePlace(place: Place)

    @Query ( "SELECT * FROM PLACE")
    fun getAllData() : LiveData<List<Place>>
}