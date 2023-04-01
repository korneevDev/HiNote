package github.mik0war.hinote.presentation

import java.util.Date

class SuccessNoteUIModel(header: String, body: String, date: Date) : NoteUIModel(header, body, date)

class FailedNoteUIModel(body: String) : NoteUIModel("", body, Date(1))

abstract class NoteUIModel(
    header: String,
    body: String,
    date: Date
) {
}