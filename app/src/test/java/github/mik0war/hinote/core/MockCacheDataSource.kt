package github.mik0war.hinote.core

import github.mik0war.hinote.data.NoteDataModel
import github.mik0war.hinote.data.cache.CacheDataSource

class MockCacheDataSource : CacheDataSource {
    private val list = ArrayList<NoteDataModel>()
    override suspend fun getNotesList(): List<NoteDataModel> {
        return list
    }

    override suspend fun save(note: NoteDataModel) {
        list.add(note)
    }

    override suspend fun remove(id: Int) {
        list.removeAt(id)
    }
}