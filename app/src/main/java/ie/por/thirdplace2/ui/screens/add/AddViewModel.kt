package ie.por.thirdplace2.ui.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.data.room.RoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {

    fun insert(thirdPlaces: ThirdPlaceModel)
            = viewModelScope.launch {
        repository.insert(thirdPlaces)
    }
}