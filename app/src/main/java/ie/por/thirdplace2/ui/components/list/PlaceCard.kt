package ie.por.thirdplace2.ui.components.list

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ie.por.thirdplace2.R
import ie.por.thirdplace2.ui.theme.Thirdplace2Theme
import ie.por.thirdplace2.ui.theme.endGradientColor
import ie.por.thirdplace2.ui.theme.startGradientColor

@Composable
fun PlaceCard(
    title: String,
    type: String,
    amenities: List<String>,
    onClickDelete: () -> Unit,
    onClickThirdPlaceDetails: () -> Unit,
) {
    Card(
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
    ) {
        PlaceCardContent(title, type, amenities, onClickDelete, onClickThirdPlaceDetails)
    }
}

@Composable
private fun PlaceCardContent(
    title: String,
    type: String,
    amenities: List<String>,
    onClickDelete: () -> Unit,
    onClickThirdPlaceDetails: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var showDeleteConfirmDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(2.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        startGradientColor,
                        endGradientColor,
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(14.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Public,
                    "Third Place",
                    Modifier.padding(end = 8.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = type,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
            if (expanded) {
                Text(text = "Amenities: ${amenities.joinToString(" | ")}", style = MaterialTheme.typography.labelSmall)
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    FilledTonalButton(onClick = onClickThirdPlaceDetails) {
                        Text(text = "More information")
                    }

                    FilledTonalIconButton(onClick = {
                        showDeleteConfirmDialog = true
                    }) {
                        Icon(Icons.Filled.Delete, "Delete place")
                    }

                    if (showDeleteConfirmDialog) {
                        ShowDeleteAlert(
                            onDismiss = { showDeleteConfirmDialog = false },
                            onDelete = onClickDelete
                        )
                    }
                }
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess
                else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Composable
fun ShowDeleteAlert(
    onDismiss: () -> Unit,
    onDelete: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = { Text(stringResource(id = R.string.confirm_delete)) },
        text = { Text(stringResource(id = R.string.confirm_delete_message)) },
        confirmButton = {
            Button(
                onClick = { onDelete() }
            ) { Text("Yes") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("No") }
        }
    )
}


@Preview
@Composable
fun PlaceCardPreview() {
    Thirdplace2Theme {
        PlaceCard(
            title = "Third Place",
            type = "Outdoors",
            amenities = listOf("Charging", "Toilets"),
            onClickDelete = { },
            onClickThirdPlaceDetails = {}
        )
    }
}