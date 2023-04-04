package github.mik0war.hinote.data.cache

import github.mik0war.hinote.data.model.NoteDataModel

interface CacheDataSource {
    suspend fun getNotesList() : List<NoteDataModel>
    suspend fun save(note: NoteDataModel)
    suspend fun remove(id: Int)
}