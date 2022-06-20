package com.place.repository

import androidx.lifecycle.LiveData
import com.place.data.PlaceDao
import com.place.model.Place


class PlaceRepository(private val placeDao: PlaceDao){
    // se implemeta las funciones de la interfaz

    val getAllDate: LiveData<List<Place>> = placeDao.getAllData()

    suspend fun addPlace(place: Place){
        placeDao.addPlace(place)

    }

    suspend fun updatePlace(place: Place){
        placeDao.updatePlace(place)

    }

    suspend fun deletePlace(place:Place){
        placeDao.updatePlace(place)
    }

}