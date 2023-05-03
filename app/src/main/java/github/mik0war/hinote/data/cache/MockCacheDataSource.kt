package github.mik0war.hinote.data.cache

import github.mik0war.hinote.data.model.NoteDataModel
import github.mik0war.hinote.domain.NoNotesException

class MockCacheDataSource : CacheDataSource {
    private val list = ArrayList<NoteDataModel>()
    override suspend fun getNotesList(): List<NoteDataModel> {
        if(list.size == 0)
            throw NoNotesException()

        return list
    }

    override suspend fun save(note: NoteDataModel) {
        list.add(note)
    }

    override suspend fun remove(id: Int) {
        list.removeAt(id)
    }
}