package ie.por.thirdplace2.ui.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.por.thirdplace2.data.ThirdPlaceModel
import ie.setu.donationx.data.fakeDonations
import ie.setu.donationx.ui.components.donate.AmountPicker
import ie.setu.donationx.ui.components.donate.DonateButton
import ie.setu.donationx.ui.components.donate.MessageInput
import ie.setu.donationx.ui.components.donate.ProgressBar
import ie.setu.donationx.ui.components.donate.RadioButtonGroup
import ie.setu.donationx.ui.components.donate.WelcomeText
import ie.setu.donationx.ui.screens.report.ReportViewModel
import ie.setu.donationx.ui.theme.DonationXTheme

@Composable
class AddScreen(modifier: Modifier = Modifier,) {
}