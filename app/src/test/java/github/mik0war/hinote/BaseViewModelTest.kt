package github.mik0war.hinote

import github.mik0war.hinote.core.TestNoteLiveData
import github.mik0war.hinote.core.data.TestCacheDataSource
import github.mik0war.hinote.core.domain.TestCurrentDateTime
import github.mik0war.hinote.core.domain.TestResourceManager
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.presentation.CachedNote
import github.mik0war.hinote.presentation.NoteLiveData
import github.mik0war.hinote.presentation.model.NoteUIModel
import github.mik0war.hinote.presentation.viewModel.NoteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest {
    private fun initViewModel(testScheduler: TestCoroutineScheduler): Pair<NoteViewModel.Base, TestNoteLiveData> {
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
            StandardTestDispatcher(testScheduler)
        )

        return Pair(viewModel, liveData)
    }

    @Test
    fun get_empty_list_test() = runTest{
        lateinit var liveData: NoteLiveData
        val viewModel = initViewModel(testScheduler).also {
            liveData = it.second}.first

        viewModel.showNoteList().join()

        val expectedList = listOf(NoteUIModel.Failed("There are no notes. Create new one"))
        Assert.assertEquals(expectedList, liveData.getNotesList())
    }
}