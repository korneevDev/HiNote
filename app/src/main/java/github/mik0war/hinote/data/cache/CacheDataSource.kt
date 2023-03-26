package github.mik0war.hinote.data.cache

import github.mik0war.hinote.data.NoteDAO

interface CacheDataSource {
    suspend fun getNotesList() : List<NoteDAO>
    suspend fun save(note: NoteDAO)
    suspend fun remove(id: Int)
}