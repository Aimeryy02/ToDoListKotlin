package com.example.todolistcd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import com.example.todolistcd.ui.theme.ToDoListCDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListCDTheme {
                funData()
            }
        }
    }
}

@Composable
fun funData() {
    val notesList = remember { mutableStateListOf<String>() }
    val task = remember { mutableStateOf(TextFieldValue()) }

    Column {
        Row{

            TextField(
                value = task.value,
                onValueChange = {
                    task.value = it
                },
                modifier = Modifier.weight(0.7f)
            )

            Button(
                onClick = {
                    notesList.add(task.value.text)
                },
                modifier = Modifier.weight(0.3f)
            ) {
                Text(text = "ADD")
            }
        }
        LazyColumn {
            itemsIndexed(notesList) { index, note ->
                val deleteButton = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append("Delete")
                    }
                }
                Row{
                    Text(text = note, Modifier.weight(0.85f))

                    ClickableText(
                        text = deleteButton, onClick = {
                            notesList.remove(note)
                        },
                        modifier = Modifier.weight(0.15f)
                    )
                }
            }
        }
    }
}