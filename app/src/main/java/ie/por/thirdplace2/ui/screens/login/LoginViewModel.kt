package ie.por.thirdplace2.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.por.thirdplace2.data.rules.Validator
import ie.por.thirdplace2.firebase.auth.Response
import ie.por.thirdplace2.firebase.services.AuthService
import ie.por.thirdplace2.firebase.services.FirebaseSignInResponse
import ie.por.thirdplace2.ui.screens.login.LoginUIEvent
import ie.por.thirdplace2.ui.screens.login.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val authService: AuthService,
)
    : ViewModel() {

    private val _loginFlow = MutableStateFlow<FirebaseSignInResponse?>(null)
    val loginFlow: StateFlow<FirebaseSignInResponse?> = _loginFlow

    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationsPassed = mutableStateOf(false)

    val currentUser: FirebaseUser?
        get() = authService.currentUser

    init {
        if (authService.currentUser != null) {
            _loginFlow.value = Response.Success(authService.currentUser!!)
        }
    }

    fun loginUser() = viewModelScope.launch {

        val email = loginUIState.value.email
        val password = loginUIState.value.password

        _loginFlow.value = Response.Loading
        val result = authService.authenticateUser(email, password)
        _loginFlow.value = result
    }

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> { loginUser() }

            else -> {}
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )

        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

    fun resetLoginFlow() {
        _loginFlow.value = null
    }
}


