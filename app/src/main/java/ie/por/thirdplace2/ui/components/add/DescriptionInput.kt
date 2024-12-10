package ie.por.thirdplace2.ui.components.add

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import ie.por.thirdplace2.R
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme

@Composable
fun DescriptionInput(modifier: Modifier = Modifier, onDescriptionChange: (String) -> Unit) {

    var description by remember { mutableStateOf("") }

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
        ),
        maxLines = 4,
        value = description,
        onValueChange = {
            description = it
            onDescriptionChange(description)
        },
        modifier = modifier.fillMaxWidth(),
        label = { Text(stringResource(R.string.hint_placeDescription)) }
    )
}

@Preview(showBackground = true)
@Composable
fun DescriptionInputPreview() {
    Thirdplace2Theme {
        DescriptionInput(
            Modifier,
            onDescriptionChange = {} )
    }
}