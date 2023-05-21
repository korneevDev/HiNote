package github.mik0war.hinote.data.model

import github.mik0war.hinote.data.cache.Note
import github.mik0war.hinote.domain.model.NoteModel

data class NoteDataModel(
    private val id: Int,
    private val header: String,
    private val body: String,
    private val dateTime: String
){
    fun mapToNoteModel(): NoteModel = NoteModel.Success(id, header, body, dateTime)
    fun mapToNote(): Note = Note(header, body, dateTime)
}