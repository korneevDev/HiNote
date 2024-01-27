package github.mik0war.hinote.di.presentation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.mik0war.hinote.presentation.DateTimeFormatter
import github.mik0war.hinote.presentation.NoteLiveData
import github.mik0war.hinote.presentation.viewModel.NoteViewModel

@Module
@InstallIn(SingletonComponent::class)
abstract class BasePresentation {
    @Binds
    abstract fun provideVM(viewModel: NoteViewModel.Base): NoteViewModel
    @Binds
    abstract fun provideLiveData(liveData: NoteLiveData.Base): NoteLiveData
    @Binds
    abstract fun provideDateFormatter(dateTimeFormatter: DateTimeFormatter.LastEditedWithLabel): DateTimeFormatter
}