package org.arba.ui.screen.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.arba.data.model.TaskItemLocal
import org.arba.theme.Blue
import org.arba.theme.Red
import org.arba.theme.Red_young
import org.arba.theme.xMediumText
import org.arba.theme.xSmallPadding
import org.arba.theme.xSmallText

//@Composable
//fun TaskItemParent(taskItemParentLocal: TaskItemParentLocal) {
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalAlignment = Alignment.Start
//    ) {
//        Text(
//            fontSize = xMediumText,
//            text = taskItemParentLocal.titleParentTime.toString(),
//            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
//            color = Color.Black,
//            fontWeight = FontWeight.Bold
//        )
//
//        Spacer(modifier = Modifier.padding(top = 10.dp))
//
//        Column {
//            taskItemParentLocal.listTaskChild.forEach { itemsChild ->
//                TaskItem(
//                    taskItemParentLocal,
//                    taskItemLocal = itemsChild,
//                    onChecked = itemsChild.isSelectedCheck,
//                    onCheckedChange = { isChecked ->
//                        println("Checkbox for ${itemsChild.titleTask} is now $isChecked")
//                        itemsChild.isSelectedCheck = isChecked
//                    }, onDeleteItem = {
////                            taskViewModel.deleteTaskItem(itemsChild.id.toLong())
//                    }
//                )
//            }
//        }
//
//    }
//}

@Composable
fun TaskItem(
    taskItemLocal: TaskItemLocal,
    onChecked: Boolean,
    onDeleteItem: () -> Unit
) {
    var isChecked by remember { mutableStateOf(onChecked) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        Card(
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 5.dp, end = 5.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            onClick = {

            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                println("respon Check ${taskItemLocal.isSelectedCheck}")
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { newChecked ->
                        isChecked = newChecked
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Blue
                    )

                )
                Spacer(modifier = Modifier.width(xSmallPadding))
                Column {
                    Text(
                        fontSize = xMediumText,
                        text = taskItemLocal.titleTask.toString(),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        fontSize = xSmallText,
                        text = taskItemLocal.dateTask.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Blue,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


        }

        var isShowDialogDelete by remember {
            mutableStateOf(false)
        }

        if (isChecked) {
            TaskDeleteItem({
                isShowDialogDelete = true
            })
        }

        if (isShowDialogDelete)
            DeleteConfirmationDialog(
                onDismiss = {
                    isShowDialogDelete = false
                },
                onDelete = {
                    onDeleteItem()
                    isShowDialogDelete = false
                }
            )
    }
}

@Composable
fun TaskDeleteItem(
    onDeleteItem: () -> Unit
) {
    Spacer(modifier = Modifier.height(10.dp)) // Jarak antara task dan delete button
    Box(
        modifier = Modifier.fillMaxWidth()
            .clickable {
                onDeleteItem()
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .background(Red_young, shape = RoundedCornerShape(8.dp))
                .border(1.dp, Red_young, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Task",
                tint = Red
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Delete task",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = Red
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteConfirmationDialog(
    onDismiss: () -> Unit,
    onDelete: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Title
            Text(
                text = "Delete",
                fontSize = 20.sp,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Description
            Text(
                text = "Are you sure to delete?",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Cancel Button
                Button(
                    modifier = Modifier.weight(1f)
                        .padding(end = 10.dp),
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Red
                    ),
                    border = BorderStroke(1.dp, Color.Red),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Cancel")
                }

                // Delete Button
                Button(
                    modifier = Modifier.weight(1f)
                        .padding(start = 10.dp),
                    onClick = {
                        onDelete()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Delete")
                }
            }

        }
    }
}

