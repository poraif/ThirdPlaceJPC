package ie.por.thirdplace2.ui.screens.add

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.data.api.RetrofitRepository
import ie.por.thirdplace2.firebase.services.AuthService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject
constructor(private val repository: RetrofitRepository,
            private val authService: AuthService
) : ViewModel() {

    var isErr = mutableStateOf(false)
    var error = mutableStateOf(Exception())
    var isLoading = mutableStateOf(false)

/*    fun insert(thirdPlaces: ThirdPlaceModel)
            = viewModelScope.launch {
        repository.insert(thirdPlaces)
    }*/

fun insert(thirdPlace: ThirdPlaceModel) =
    viewModelScope.launch {
        try {
            isLoading.value = true
            repository.insert(authService.email!!,thirdPlace)
            isErr.value = false
            isLoading.value = false
        } catch (e: Exception) {
            isErr.value = true
            error.value = e
            isLoading.value = false
        }
    }
}