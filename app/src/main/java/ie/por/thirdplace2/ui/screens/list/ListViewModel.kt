package ie.por.thirdplace2.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.data.room.RoomRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {
    private val _thirdPlaces
            = MutableStateFlow<List<ThirdPlaceModel>>(emptyList())
    val uiThirdPlaces: StateFlow<List<ThirdPlaceModel>>
            = _thirdPlaces.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll().collect { listOfThirdPlaces ->
                _thirdPlaces.value = listOfThirdPlaces
            }
        }
    }

    fun deleteThirdPlace(thirdPlace: ThirdPlaceModel) {
        viewModelScope.launch {
            repository.delete(thirdPlace)
        }
    }
}