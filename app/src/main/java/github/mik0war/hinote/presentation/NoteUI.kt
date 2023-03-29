package github.mik0war.hinote.presentation

import java.util.Date

class SuccessNoteUI(header: String, body: String, date: Date) : NoteUI(header, body, date)

class FailedNoteUI(header: String, body: String, date: Date) : NoteUI(header, body, date)

abstract class NoteUI(
    header: String,
    body: String,
    date: Date
) {
}