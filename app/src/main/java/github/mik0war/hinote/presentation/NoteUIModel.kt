package github.mik0war.hinote.presentation

class SuccessNoteUIModel(header: String, body: String, dateTime: String) : NoteUIModel(header, body, dateTime)

class FailedNoteUIModel(body: String) : NoteUIModel(body=body)

abstract class NoteUIModel(
    header: String = "",
    body: String,
    dateTime: String = ""
)