package  ie.por.thirdplace2.ui.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.por.thirdplace2.R
import  ie.por.thirdplace2.ui.components.general.Centre
import  ie.por.thirdplace2.ui.theme.Thirdplace2Theme

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier.background(MaterialTheme.colorScheme.secondary),
    ) {
        Centre(Modifier
            .fillMaxWidth()
            .padding(top = 48.dp,)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "homer image",
                modifier = Modifier.size(350.dp)
            )
        }
        Centre(Modifier.fillMaxSize()) {
            Text(color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 34.sp,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.about_message)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    Thirdplace2Theme {
        AboutScreen( modifier = Modifier)
    }
}