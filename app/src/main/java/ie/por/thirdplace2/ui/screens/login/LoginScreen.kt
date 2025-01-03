package ie.por.thirdplace2.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ie.por.thirdplace2.R
import ie.por.thirdplace2.firebase.auth.Response
import ie.por.thirdplace2.navigation.Home
import ie.por.thirdplace2.navigation.Login
import ie.por.thirdplace2.ui.components.general.ButtonComponent
import ie.por.thirdplace2.ui.components.general.GoogleSignInButtonComponent
import ie.por.thirdplace2.ui.components.general.HeadingLogoComponent
import ie.por.thirdplace2.ui.components.general.HeadingTextComponent
import ie.por.thirdplace2.ui.components.general.MyTextFieldComponent
import ie.por.thirdplace2.ui.components.general.PasswordTextFieldComponent
import ie.por.thirdplace2.ui.components.general.ShowLoader
import ie.por.thirdplace2.ui.components.general.UnderLinedTextComponent
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme

@Composable
fun LoginScreen(
    onLogin: () -> Unit = {},
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var isEnabled by remember { mutableStateOf(false) }
    val loginFlow = loginViewModel.loginFlow.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))
                HeadingLogoComponent()
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(20.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(30.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                        onLogin()
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )
                isEnabled = loginViewModel.allValidationsPassed.value

                // Google Button here
                Spacer(modifier = Modifier.height(10.dp))
                val context = LocalContext.current
                GoogleSignInButtonComponent {
                    loginViewModel.signInWithGoogleCredentials(context)
                }
            }
        }
    }

    loginFlow.value?.let {
        when (it) {
            is Response.Failure -> {
                val context = LocalContext.current
                Toast.makeText(context, it.e.message, Toast.LENGTH_LONG).show()
                navController.popBackStack()
                navController.navigate(Login.route)
            }
            is Response.Loading -> {
                ShowLoader(message = "Please Wait...")
            }
            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(Home.route) {
                        popUpTo(Login.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    Thirdplace2Theme {
        PreviewLoginScreen()
    }
}

@Composable
fun PreviewLoginScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                //  NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(10.dp))
                HeadingLogoComponent()
                Spacer(modifier = Modifier.height(10.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        //loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = true
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        //loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = true
                )

                Spacer(modifier = Modifier.height(10.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(10.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        //loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = false
                )
                Spacer(modifier = Modifier.height(10.dp))
                GoogleSignInButtonComponent {
                    //  loginViewModel.oneTapSignIn()
                }
            }
        }

    }
}
