package github.mik0war.hinote.data

import github.mik0war.hinote.data.entity.NoteDataModel

interface MutableCacheDataSource<T> {

    fun cache(value: T)
    fun getData(): T

    class Base : MutableCacheDataSource<NoteDataModel> {
        private var cachedDataModel: NoteDataModel? = null
        override fun cache(value: NoteDataModel) {
            cachedDataModel = value
        }

        override fun getData() =
            cachedDataModel.also { cachedDataModel = null } ?: throw IllegalStateException()
    }
}