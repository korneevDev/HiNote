package github.mik0war.hinote.data.entity

import github.mik0war.hinote.data.cache.room.Note
import github.mik0war.hinote.domain.entity.NoteModel

data class NoteDataModel(
    private val id: Int,
    private val header: String,
    private val body: String,
    private val dateTime: String,
    private val lastEditedDateTime: String?
){
    fun mapToNoteModel(): NoteModel =
        NoteModel.Success(id, header, body, dateTime, lastEditedDateTime)
    fun mapToNote(): Note = Note(header, body, dateTime, lastEditedDateTime)
}