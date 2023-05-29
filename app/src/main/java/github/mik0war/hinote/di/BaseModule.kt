package github.mik0war.hinote.di

import dagger.Binds
import dagger.Module
import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.data.entity.NoteDataModel
import github.mik0war.hinote.domain.CurrentDateTime
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.domain.ResourceManager
import github.mik0war.hinote.presentation.DateTimeFormatter
import github.mik0war.hinote.presentation.NoteLiveData
import github.mik0war.hinote.presentation.viewModel.NoteViewModel

@Module
abstract class BaseModule {
    @Binds
    abstract fun provideBaseResourceManager(resourceManager: ResourceManager.Base): ResourceManager
    @Binds
    abstract fun provideExceptionHandler(handler: ExceptionHandler.Base): ExceptionHandler
    @Binds
    abstract fun provideCurrentDateTime(dateTime: CurrentDateTime.Base): CurrentDateTime
    @Binds
    abstract fun provideInteractor(interactor: NoteInteractor.Base): NoteInteractor
    @Binds
    abstract fun provideRepo(repository: NoteRepository.Base): NoteRepository
    @Binds
    abstract fun provideCacheDataSource(dataSource: CacheDataSource.Base): CacheDataSource
    @Binds
    abstract fun provideMapper(mapper: MapperParametrised.ToDataModel): MapperParametrised<NoteDataModel>
    @Binds
    abstract fun provideVM(viewModel: NoteViewModel.Base): NoteViewModel
    @Binds
    abstract fun provideLiveData(liveData: NoteLiveData.Base): NoteLiveData
    @Binds
    abstract fun provideDateFormatter(dateTimeFormatter: DateTimeFormatter.LastEditedWithLabel): DateTimeFormatter
}