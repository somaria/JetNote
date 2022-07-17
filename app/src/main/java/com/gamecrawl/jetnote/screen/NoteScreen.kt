package com.gamecrawl.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamecrawl.jetnote.R
import com.gamecrawl.jetnote.components.NoteButton
import com.gamecrawl.jetnote.components.NoteInputText
import com.gamecrawl.jetnote.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Notifications")
        }, backgroundColor = Color(0xFFDFE9E9))
        //column
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            NoteInputText(
                text = title,
                label = "Title",
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                onTextChanged = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                        title = it
                    }
                })

            NoteInputText(
                text = description,
                label = "Add A Note",
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                onTextChanged = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) {
                        description = it
                    }
                })

            NoteButton(text = "Save Note", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onAddNote(Note(title = title, description = description))
                    title = ""
                    description = ""
                    Toast.makeText(
                        context,
                        "Note Saved",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn() {
            items(notes) { note ->
                NoteRow(note = note, modifier = Modifier.padding(10.dp),
                    onNoteClicked = {
                        onRemoveNote(note)
                    })
            }
        }
    }
}

@Composable
fun NoteRow(note: Note, modifier: Modifier, onNoteClicked: (Note)->Unit) {
    Surface(modifier = modifier
        .padding(4.dp)
        .clip(shape = RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth(),
        color = Color(0xFFE0E0E0),
        elevation = 6.dp
    ) {
        Column(
            modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")), style = MaterialTheme.typography.caption)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = emptyList(), onAddNote = {}, onRemoveNote = {})
}