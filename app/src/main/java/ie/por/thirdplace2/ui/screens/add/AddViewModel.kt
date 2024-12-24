package ie.por.thirdplace2.ui.screens.add

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.firebase.services.AuthService
import ie.por.thirdplace2.firebase.services.FirestoreService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService) : ViewModel() {
    var isErr = mutableStateOf(false)
    var error = mutableStateOf(Exception())
    var isLoading = mutableStateOf(false)


/*    fun insert(thirdPlaces: ThirdPlaceModel)
            = viewModelScope.launch {
        repository.insert(thirdPlaces)
    }*/

fun insert(thirdPlace: ThirdPlaceModel, uri: Uri) =
    viewModelScope.launch {
        try {
            isLoading.value = true
            repository.insert(authService.email!!,thirdPlace, uri)
            isErr.value = false
            isLoading.value = false
        } catch (e: Exception) {
            isErr.value = true
            error.value = e
            isLoading.value = false
        }
    }
}