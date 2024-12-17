package ie.por.thirdplace2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ie.por.thirdplace2.ui.screens.home.HomeScreen
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Thirdplace2Theme { HomeScreen() }
        }
    }
}