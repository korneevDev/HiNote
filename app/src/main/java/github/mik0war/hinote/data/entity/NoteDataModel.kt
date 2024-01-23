package github.mik0war.hinote.data.entity

import github.mik0war.hinote.data.cache.room.Note
import github.mik0war.hinote.domain.entity.NoteModel

data class NoteDataModel(
    private val id: Int,
    private val header: String,
    private val body: String,
    private val dateTime: Long,
    private val lastEditedDateTime: Long?,
    private val mainColor: Int,
    private val buttonsColor: Int
) {
    fun mapToNoteModel(): NoteModel =
        NoteModel.Success(id, header, body, dateTime, lastEditedDateTime, mainColor, buttonsColor)

    fun mapToNote(): Note =
        Note(header, body, dateTime, lastEditedDateTime, mainColor, buttonsColor)
}