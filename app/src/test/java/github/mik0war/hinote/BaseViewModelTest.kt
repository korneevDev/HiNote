package github.mik0war.hinote

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.core.MockCacheDataSource
import github.mik0war.hinote.core.MockNoteLiveData
import github.mik0war.hinote.core.MockResourceManager
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.presentation.NoteViewModel
import github.mik0war.hinote.presentation.model.NoteUIModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class BaseViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest {
        val repository = NoteRepository.Base(MockCacheDataSource())
        val interactor = NoteInteractor.Base(
            repository,
            ExceptionHandler.Base(
                MockResourceManager()
            ),
            MapperParametrised.ToDataModel()
        )


        val liveData = MockNoteLiveData()

        val viewModel = NoteViewModel.Base(
            interactor,
            liveData,
            UnconfinedTestDispatcher()
            )

        viewModel.getNoteList()
        val expectedList = listOf(NoteUIModel.Failed("No notes"))
        Thread.sleep(100)
        Assert.assertEquals(expectedList, liveData.notesList)
    }
}