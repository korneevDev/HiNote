package github.mik0war.hinote.core

import github.mik0war.hinote.data.model.NoteDataModel

class TestNoteModel(
    val id: Int,
    var header: String,
    var body: String,
    val dateTime: String
){
    fun mapToNodeDataModel() = NoteDataModel(id, header, body, dateTime)
}