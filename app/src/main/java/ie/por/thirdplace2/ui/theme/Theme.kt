package ie.por.thirdplace2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Define the Light Color Scheme with specified colors
private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,
    onPrimary = ColorOnPrimary,

    secondary = ColorSecondary,
    onSecondary = ColorOnSecondary,

    background = ColorBackground,
    onBackground = ColorOnBackground,

    surface = ColorSurface,
    onSurface = ColorOnSurface
)

// Theme Definition
@Composable
fun Thirdplace2Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}