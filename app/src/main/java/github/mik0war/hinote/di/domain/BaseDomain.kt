package github.mik0war.hinote.di.domain

import dagger.Binds
import dagger.Module
import github.mik0war.hinote.domain.CurrentDateTime
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.core.ResourceManager

@Module
abstract class BaseDomain {
    @Binds
    abstract fun provideBaseResourceManager(resourceManager: ResourceManager.Base): ResourceManager
    @Binds
    abstract fun provideExceptionHandler(handler: ExceptionHandler.Base): ExceptionHandler
    @Binds
    abstract fun provideCurrentDateTime(dateTime: CurrentDateTime.Base): CurrentDateTime
    @Binds
    abstract fun provideInteractor(interactor: NoteInteractor.Base): NoteInteractor
}