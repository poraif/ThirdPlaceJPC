package ie.por.thirdplace2.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.por.thirdplace2.firebase.services.AuthService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authService: AuthService,
    private val auth: FirebaseAuth,
) : ViewModel() {

    val displayName get() = auth.currentUser?.displayName.toString()
    val email get() = auth.currentUser?.email.toString()

    fun signOut() {
        viewModelScope.launch { authService.signOut() }
    }
}