package github.mik0war.hinote.core

import github.mik0war.hinote.data.model.NoteDataModel
import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.domain.NoNotesException

class MockCacheDataSource : CacheDataSource {
    private val list = ArrayList<NoteDataModel>()
    override suspend fun getNotesList(): List<NoteDataModel> {
        if(list.size == 0)
            throw NoNotesException()

        return list
    }

    override suspend fun save(header: String, body: String, dateTime: String) {
        TODO("Not yet implemented")
    }

    override suspend fun update(id: Int, newHeader: String, newBody: String) {
        TODO("Not yet implemented")
    }

    override suspend fun remove(id: Int): NoteDataModel {
        TODO("Not yet implemented")
    }

}