package github.mik0war.hinote.data

import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.data.model.NoteDataModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface NoteRepository {
    suspend fun getNotesList() : List<NoteDataModel>
    suspend fun saveNote(header: String, body: String, dateTime: String)
    suspend fun updateNote(id: Int, header: String, body: String)
    suspend fun removeNote(id: Int): NoteDataModel

    class Base(
        private val cacheDataSource: CacheDataSource,
        private val dispatcher: CoroutineDispatcher =  Dispatchers.IO
    ) : NoteRepository {
        override suspend fun getNotesList(): List<NoteDataModel> =
            handle { cacheDataSource.getNotesList() }
        override suspend fun saveNote(header: String, body: String, dateTime: String) =
            handle { cacheDataSource.save(header, body, dateTime) }

        override suspend fun updateNote(id: Int, header: String, body: String)  =
            handle { cacheDataSource.update(id, header, body) }

        override suspend fun removeNote(id: Int) =
            handle { cacheDataSource.remove(id) }

        private suspend fun <T>handle(block: suspend ()-> T) =
            withContext(dispatcher) {
                try {
                    return@withContext block.invoke()
                } catch (e: Exception) {
                    throw e
                }
            }
    }
}