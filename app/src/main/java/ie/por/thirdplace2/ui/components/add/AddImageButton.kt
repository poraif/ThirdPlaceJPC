package ie.por.thirdplace2.ui.components.add

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.por.thirdplace2.R
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme
import ie.por.thirdplace2.ui.screens.add.AddViewModel
import ie.por.thirdplace2.ui.screens.list.ListViewModel
import timber.log.Timber

@Composable

fun AddImageButton(
    modifier: Modifier = Modifier,
    thirdPlace: ThirdPlaceModel,
    addViewModel: AddViewModel = hiltViewModel(),
    listViewModel: ListViewModel = hiltViewModel(),
    onAddClicked: () -> Unit,
) {
    val string = R.string.button_addImage
}