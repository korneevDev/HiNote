package github.mik0war.hinote

import github.mik0war.hinote.core.TestNoteLiveData
import github.mik0war.hinote.core.data.TestCacheDataSource
import github.mik0war.hinote.core.domain.TestCurrentDateTime
import github.mik0war.hinote.core.domain.TestResourceManager
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.presentation.CachedNote
import github.mik0war.hinote.presentation.model.NoteUIModel
import github.mik0war.hinote.presentation.viewModel.NoteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Assert
import org.junit.Test

class BaseViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runBlocking(dispatcher) {
        val repository = NoteRepository.Base(TestCacheDataSource())
        val interactor = NoteInteractor.Base(
            repository,
            ExceptionHandler.Base(
                TestResourceManager()
            ),
            TestCurrentDateTime(),
        )


        val liveData = TestNoteLiveData()

        val viewModel = NoteViewModel.Base(
            interactor,
            liveData,
            CachedNote.Base(),
            dispatcher
            )

        viewModel.showNoteList()
        val expectedList = listOf(NoteUIModel.Failed("There are no notes. Create new one"))
        Assert.assertEquals(expectedList, liveData.notesList)
    }
}