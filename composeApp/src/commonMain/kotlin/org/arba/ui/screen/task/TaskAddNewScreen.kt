package org.arba.ui.screen.task

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.arba.data.model.TaskItemLocal
import org.arba.theme.Blue
import org.arba.theme.Gray_Text
import org.arba.ui.screen.dialog.ShowDatePickerDialog
import org.arba.ui.screen.dialog.ShowTimePickerDialog
import org.arba.viewmodel.TaskViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import todoapps.composeapp.generated.resources.Res
import todoapps.composeapp.generated.resources.baseline_access_time_24

@OptIn(ExperimentalMaterial3Api::class, KoinExperimentalAPI::class)
@Composable
fun TaskAddNewScreen(navController: NavController) {

    val taskViewModel = koinViewModel<TaskViewModel>()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var isTimeEnabled by remember { mutableStateOf(false) }

    var stateDatePickerDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    androidx.compose.material.Text("Add New Task")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title Field

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = "Title",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )

                androidx.compose.material.OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(Gray_Text),
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Text("Title Task")
                    }
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            // Description Field
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = "Descriptions",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )

                androidx.compose.material.OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 10.dp)
                        .height(120.dp)
                        .background(Gray_Text),
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Text("Descriptions Task")
                    }
                )
            }


            Spacer(modifier = Modifier.height(16.dp))


            // Date Field
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = "Date",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )

                androidx.compose.material.OutlinedTextField(
                    readOnly = true,
                    enabled = false,
                    value = date,
                    onValueChange = { date = it },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(Gray_Text)
                        .clickable {
                            stateDatePickerDialog = true
                        },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = ""
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    placeholder = {
                        Text("Select Date")
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Time Toggle
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 6.dp, end = 15.dp, top = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(Res.drawable.baseline_access_time_24),
                    contentDescription = "Time"
                )

                Text(
                    text = time.ifEmpty { "Time" },
                    modifier = Modifier.weight(1f)
                        .padding(start = 10.dp),
                )
                Switch(
                    modifier = Modifier.size(20.dp),
                    checked = isTimeEnabled,
                    onCheckedChange = { isTimeEnabled = it }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Buttons
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f)
                            .clip(RoundedCornerShape(5.dp)),
                        onClick = { /* Handle Cancel */ },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color.Gray)
                    ) {
                        Text(text = "Cancel", color = Color.Black)
                    }

                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {

                            saveDataTask(
                                navController,
                                taskViewModel,
                                title.toString(),
                                description.toString(),
                                date
                            )
                        },
                        colors = ButtonDefaults.buttonColors(Blue),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Save", color = Color.White)
                    }
                }
            }

        }


        if (stateDatePickerDialog) {
            ShowDatePickerDialog(
                stateDatePickerDialog,
                onDoneClick = {
                    date = it
                    println("respon Date $it")
                    stateDatePickerDialog = false
                },
                onDismis = {
                    stateDatePickerDialog = false
                }
            )
        }

        if (isTimeEnabled) {
            ShowTimePickerDialog(
                isTimeEnabled,
                onDoneClick = {
                    time = it
                    println("respon Date $it")
                    isTimeEnabled = false
                },
                onDismis = {
                    isTimeEnabled = false
                }
            )
        }
    }


}

fun saveDataTask(
    navController: NavController,
    taskViewModel: TaskViewModel,
    titleField: String, descField: String,
    date: String
) {
    val taskItemLocal = TaskItemLocal(
        0,
        titleTask = titleField,
        descField,
        date,
        false
    )
    taskViewModel.addTaskItem(taskItemLocal)
    navController.popBackStack()
}
