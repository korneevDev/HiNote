package github.mik0war.hinote.data

import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.data.entity.NoteDataModel
import github.mik0war.hinote.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface NoteRepository {
    suspend fun getNotesList() : List<NoteDataModel>
    suspend fun saveNote(header: String, body: String, dateTime: String)
    suspend fun saveCachedNote()
    suspend fun updateNote(id: Int, header: String, body: String)
    suspend fun removeNote(id: Int)

    class Base @Inject constructor(
        private val cacheDataSource: CacheDataSource,
        @IODispatcher private val dispatcher: CoroutineDispatcher
    ) : NoteRepository {
        private var cachedNote: NoteDataModel? = null
        override suspend fun getNotesList(): List<NoteDataModel> =
            handle { cacheDataSource.getNotesList() }
        override suspend fun saveNote(header: String, body: String, dateTime: String) =
            handle { cacheDataSource.save(header, body, dateTime) }

        override suspend fun saveCachedNote() {
            handle { cacheDataSource.save(cachedNote!!) }
            cachedNote = null
        }

        override suspend fun updateNote(id: Int, header: String, body: String)  =
            handle { cacheDataSource.update(id, header, body) }

        override suspend fun removeNote(id: Int) {
            cachedNote = handle { cacheDataSource.remove(id) }
        }

        private suspend inline fun <T>handle(crossinline block: suspend ()-> T) =
            withContext(dispatcher) {
                try {
                    return@withContext block.invoke()
                } catch (e: Exception) {
                    throw e
                }
            }
    }
}