package github.mik0war.hinote.core

import github.mik0war.hinote.data.NoteDAO
import github.mik0war.hinote.data.cache.CacheDataSource

class TestCacheDataSource : CacheDataSource {
    private val list = ArrayList<NoteDAO>()
    override suspend fun getNotesList(): List<NoteDAO> {
        return list
    }

    override suspend fun save(note: NoteDAO) {
        list.add(note)
    }

    override suspend fun remove(id: Int) {
        list.removeAt(id)
    }
}