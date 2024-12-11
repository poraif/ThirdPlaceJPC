package ie.por.thirdplace2.ui.components.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ie.por.thirdplace2.R
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme

@Composable
fun TypeRadioButtonGroup(modifier: Modifier = Modifier,
                     onPlaceTypeChange: (String) -> Unit) {

    val radioOptions = listOf(
        stringResource(R.string.type_outdoors),
        stringResource(R.string.type_cultural),
        stringResource(R.string.type_food),
        stringResource(R.string.type_activity),
        stringResource(R.string.type_community)
    )
    var type by remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ){
        radioOptions.forEach { typeText ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (typeText == type),
                    onClick = {
                        type = typeText
                        onPlaceTypeChange(type)
                    }
                )
                Text(
                    text = typeText,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RadioButtonPreview() {
    Thirdplace2Theme {
        TypeRadioButtonGroup(
            Modifier,
            onPlaceTypeChange = {}
        )
    }
}