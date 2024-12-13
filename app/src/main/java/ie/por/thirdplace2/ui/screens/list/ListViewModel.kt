package ie.por.thirdplace2.ui.screens.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.data.api.RetrofitRepository
import ie.por.thirdplace2.firebase.services.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject
constructor(private val repository: RetrofitRepository,
            private val authService: AuthService
) : ViewModel() {
    private val _thirdPlaces
            = MutableStateFlow<List<ThirdPlaceModel>>(emptyList())
    val uiThirdPlaces: StateFlow<List<ThirdPlaceModel>>
            = _thirdPlaces.asStateFlow()
    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

/*    init {
        viewModelScope.launch {
            repository.getAll().collect { listOfThirdPlaces ->
                _thirdPlaces.value = listOfThirdPlaces
            }
        }
    }*/

    init { getThirdPlaces() }

fun getThirdPlaces() {
    viewModelScope.launch {
        try {
            isLoading.value = true
            _thirdPlaces.value = repository.getAll(authService.email!!)
            isErr.value = false
            isLoading.value = false
            Timber.i("RVM : ${_thirdPlaces.value}")
        }
        catch(e:Exception) {
            isErr.value = true
            isLoading.value = false
            error.value = e
            Timber.i("RVM Error ${e.message}")
        }
    }
}

    fun deleteThirdPlace(thirdPlace: ThirdPlaceModel) {
        viewModelScope.launch {
            repository.delete(authService.email!!,thirdPlace)
        }
    }
}