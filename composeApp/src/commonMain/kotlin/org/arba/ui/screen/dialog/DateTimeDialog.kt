package org.arba.ui.screen.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import network.chaintech.kmp_date_time_picker.ui.datepicker.WheelDatePickerView
import network.chaintech.kmp_date_time_picker.ui.timepicker.WheelTimePickerView
import network.chaintech.kmp_date_time_picker.utils.DateTimePickerView
import network.chaintech.kmp_date_time_picker.utils.now


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



fun getLocalDateTimeFromLong(long: Long): LocalDateTime {
    return Instant.fromEpochMilliseconds(long).toLocalDateTime(TimeZone.currentSystemDefault())
}