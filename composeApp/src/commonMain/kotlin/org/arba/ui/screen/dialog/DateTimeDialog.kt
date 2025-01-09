package org.arba.ui.screen.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohamedrejeb.calf.ui.datepicker.AdaptiveDatePicker
import com.mohamedrejeb.calf.ui.datepicker.rememberAdaptiveDatePickerState
import com.mohamedrejeb.calf.ui.sheet.AdaptiveBottomSheet
import com.mohamedrejeb.calf.ui.sheet.rememberAdaptiveSheetState
import com.mohamedrejeb.calf.ui.timepicker.AdaptiveTimePicker
import com.mohamedrejeb.calf.ui.timepicker.rememberAdaptiveTimePickerState
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerDialog
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerView
import network.chaintech.kmp_date_time_picker.ui.timepicker.WheelTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.now
import org.arba.utils.DateTime.format


@Composable
fun ShowDatePickerDialog(
    isShowClick: Boolean,
    onDismis: () -> Unit,
    onDoneClick: (String) -> Unit
) {
    WheelDatePickerView(
        title =  "Set Date",
        height = 150.dp,
        showDatePicker = isShowClick,
        dateTimePickerView = DateTimePickerView.BOTTOM_SHEET_VIEW,
        rowCount = 3,
        yearsRange = 1930..LocalDate.now().year,
        onDismiss = {
            onDismis()
        },
        onDoneClick = {
            onDoneClick(it.toString())
        },
        titleStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        doneLabelStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    )
}

@Composable
fun ShowTimePickerDialog(
    isShowClick: Boolean,
    onDismis: () -> Unit,
    onDoneClick: (String) -> Unit
) {
    WheelTimePickerView(
        title = "Set Time",
        height = 150.dp,
        showTimePicker = isShowClick,
        dateTimePickerView = DateTimePickerView.BOTTOM_SHEET_VIEW,
        rowCount = 3,
        onDismiss = {
            onDismis()
        },
        onDoneClick = {
            onDoneClick(it.toString())
        },
        titleStyle = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        doneLabelStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    )
}


fun getTimeInLong(date: Long?, hour: Int, minute: Int): Long {
    val localDate = getLocalDateTimeFromLong(date ?: Clock.System.now().toEpochMilliseconds())
    val localTime = LocalTime(hour, minute)
    val localObj = LocalDateTime(localDate.date, localTime)
    return localObj.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

fun getTimeProgress(date: Long?, hour: Int, minute: Int): String {
    val localDate = getLocalDateTimeFromLong(date ?: Clock.System.now().toEpochMilliseconds())
    val localTime = LocalTime(hour, minute)
    val localObj = LocalDateTime(localDate.date, localTime)
    return localObj.format("hh:mm a")

}


fun getLocalDateTimeFromLong(long: Long): LocalDateTime {
    return Instant.fromEpochMilliseconds(long).toLocalDateTime(TimeZone.currentSystemDefault())
}