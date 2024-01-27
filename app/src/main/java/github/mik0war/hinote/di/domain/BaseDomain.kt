package github.mik0war.hinote.di.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.mik0war.hinote.core.StringResourceManager
import github.mik0war.hinote.domain.CurrentDateTime
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor

@Module
@InstallIn(SingletonComponent::class)
abstract class BaseDomain {
    @Binds
    abstract fun provideBaseResourceManager(stringResourceManager: StringResourceManager.Base): StringResourceManager
    @Binds
    abstract fun provideExceptionHandler(handler: ExceptionHandler.Base): ExceptionHandler
    @Binds
    abstract fun provideCurrentDateTime(dateTime: CurrentDateTime.Base): CurrentDateTime
    @Binds
    abstract fun provideInteractor(interactor: NoteInteractor.Base): NoteInteractor
}