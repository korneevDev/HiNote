package github.mik0war.hinote.data

import github.mik0war.hinote.data.cache.CacheDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface NoteRepository {
    suspend fun getNotesList() : List<NoteDataModel>
    suspend fun saveNote(note: NoteDataModel)
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
        override suspend fun saveNote(note: NoteDataModel) = cacheDataSource.save(note)
        override suspend fun removeNote(id: Int) = cacheDataSource.remove(id)
    }
}