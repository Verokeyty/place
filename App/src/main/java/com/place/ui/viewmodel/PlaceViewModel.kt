package com.place.ui.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.place.data.PlaceDatabase
import com.place.model.Place
import com.place.repository.PlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceViewModel(application: Application) : AndroidViewModel(application) {

   val getAllDate: LiveData<List<Place>>

  private val repository: PlaceRepository

  init {
      val placeDao = PlaceDatabase.getDatabase(application).placeDao()
      repository = PlaceRepository(placeDao)
      getAllDate = repository.getAllDate
  }

    fun addPlace (place: Place){
    viewModelScope.launch(Dispatchers.IO){
        repository.addPlace(place)
    }
}
    fun deletePlace (place: Place){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletePlace(place)
        }

    }


    fun updatePlace(place: Place){
        viewModelScope.launch (Dispatchers.IO){
            repository.updatePlace(place);
        }
    }
}