package github.mik0war.hinote.data.entity

import github.mik0war.hinote.data.cache.room.Note
import github.mik0war.hinote.domain.entity.NoteModel

data class NoteDataModel(
    private val id: Int,
    private val header: String,
    private val body: String,
    private val dateTime: Long,
    private val lastEditedDateTime: Long?
){
    fun mapToNoteModel(): NoteModel =
        NoteModel.Success(id, header, body, dateTime, lastEditedDateTime)
    fun mapToNote(): Note = Note(header, body, dateTime, lastEditedDateTime)
}