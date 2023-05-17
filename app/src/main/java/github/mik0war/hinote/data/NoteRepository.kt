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
    suspend fun removeNote(id: Int)

    class Base(
        private val cacheDataSource: CacheDataSource,
        private val dispatcher: CoroutineDispatcher =  Dispatchers.IO
    ) : NoteRepository {
        override suspend fun getNotesList(): List<NoteDataModel> = withContext(dispatcher){
            try {
                return@withContext cacheDataSource.getNotesList()
            } catch (e: Exception){
                throw e
            }
        }
        override suspend fun saveNote(header: String, body: String, dateTime: String) =
            withContext(dispatcher) {
            try {
                return@withContext cacheDataSource.save(header, body, dateTime)
            } catch (e: Exception) {
                throw e
            }
        }

        override suspend fun updateNote(id: Int, header: String, body: String)  =
            withContext(dispatcher) {
                try {
                    return@withContext cacheDataSource.update(id, header, body)
                } catch (e: Exception) {
                    throw e
                }
            }

        override suspend fun removeNote(id: Int) =
            withContext(dispatcher) {
                try {
                    return@withContext cacheDataSource.remove(id)
                } catch (e: Exception) {
                    throw e
                }
            }
    }
}