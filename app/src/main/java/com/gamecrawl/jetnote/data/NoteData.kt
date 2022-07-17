package com.gamecrawl.jetnote.data

import com.gamecrawl.jetnote.model.Note

class NoteData {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(
                title = "This is the first note",
                description = "This is the first note's description",
            ),
            Note(
                title = "This is the second note",
                description = "This is the second note's description",
            ),
            Note(
                title = "This is the third note",
                description = "This is the third note's description",
            ),
            Note(
                title = "This is the fourth note",
                description = "This is the fourth note's description",
            ),
            Note(
                title = "This is the fifth note",
                description = "This is the fifth note's description",
            ),

        )
    }
}