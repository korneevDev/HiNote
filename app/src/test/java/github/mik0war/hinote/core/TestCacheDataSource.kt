package github.mik0war.hinote.core

import github.mik0war.hinote.data.model.NoteDataModel
import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.domain.NoNotesException

class TestCacheDataSource : CacheDataSource {
    private val list = ArrayList<TestNoteModel>()
    private var i = 0
    override suspend fun getNotesList(): List<NoteDataModel> {
        if(list.size == 0)
            throw NoNotesException()

        return list.map { note -> note.mapToNodeDataModel()}
    }

    override suspend fun save(header: String, body: String, dateTime: String) {
        list.add(i, TestNoteModel(i++, header, body, dateTime))
    }

    override suspend fun update(id: Int, newHeader: String, newBody: String) {
        val currentNote = list[id]
        list[id] = TestNoteModel(currentNote.id, newHeader, newBody, currentNote.dateTime)
    }

    override suspend fun remove(id: Int): NoteDataModel {
        return list[id].also { list.remove(it) }.mapToNodeDataModel()
    }
}