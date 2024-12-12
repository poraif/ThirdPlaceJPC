package ie.por.thirdplace2.ui.screens.details


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.data.api.RetrofitRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(private val repository: RetrofitRepository,
            savedStateHandle: SavedStateHandle
) : ViewModel() {

    var thirdPlace = mutableStateOf(ThirdPlaceModel())
    val id: String = checkNotNull(savedStateHandle["id"])
    var isErr = mutableStateOf(false)
    var error = mutableStateOf(Exception())
    var isLoading = mutableStateOf(false)

    init {
        viewModelScope.launch {
            try {
                isLoading.value = true
                thirdPlace.value = repository.get(id)[0]
                isErr.value = false
                isLoading.value = false
            } catch (e: Exception) {
                isErr.value = true
                error.value = e
                isLoading.value = false
            }
        }
    }

    fun updateThirdPlace(thirdPlace: ThirdPlaceModel) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                repository.update(thirdPlace)
                isErr.value = false
                isLoading.value = false
            } catch (e: Exception) {
                isErr.value = true
                error.value = e
                isLoading.value = false
            }
        }
    }
}