package github.mik0war.hinote.di.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.data.entity.NoteDataModel

@Module
@InstallIn(SingletonComponent::class)
abstract class BaseData {
    @Binds
    abstract fun provideRepo(repository: NoteRepository.Base): NoteRepository
    @Binds
    abstract fun provideCacheDataSource(dataSource: CacheDataSource.Base): CacheDataSource
    @Binds
    abstract fun provideMapper(mapper: MapperParametrised.ToDataModel): MapperParametrised<NoteDataModel>
}