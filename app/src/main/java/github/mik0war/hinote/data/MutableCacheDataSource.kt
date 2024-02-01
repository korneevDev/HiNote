package github.mik0war.hinote.data

import github.mik0war.hinote.data.entity.NoteDataModel
import javax.inject.Inject

interface MutableCacheDataSource<T> {

    fun cache(value: T)
    fun getData(): T

    class Base @Inject constructor() : MutableCacheDataSource<NoteDataModel> {
        private var cachedDataModel: NoteDataModel? = null
        override fun cache(value: NoteDataModel) {
            cachedDataModel = value
        }

        override fun getData() =
            cachedDataModel.also { cachedDataModel = null } ?: throw IllegalStateException()
    }
}