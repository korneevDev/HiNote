package github.mik0war.hinote.presentation

import github.mik0war.hinote.core.data.TestCacheDataSource
import github.mik0war.hinote.core.domain.TestCurrentDateTime
import github.mik0war.hinote.core.domain.TestResourceManager
import github.mik0war.hinote.core.presentation.TestNoteLiveData
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.presentation.model.NoteUIModel
import github.mik0war.hinote.presentation.viewModel.NoteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest {
    private fun initViewModel(
        testScheduler: TestCoroutineScheduler
    ): Pair<NoteViewModel.Base, TestNoteLiveData> {
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
            StandardTestDispatcher(testScheduler)
        )

        return Pair(viewModel, liveData)
    }

    @Test
    fun get_empty_list_test() = runTest{

        val (viewModel, liveData) = initViewModel(testScheduler)

        viewModel.showNoteList().join()

        val expectedList = listOf(NoteUIModel.Failed("There are no notes. Create new one"))
        assertEquals(expectedList, liveData.getNotesList())
    }

    @Test
    fun saving_note_test() = runTest {
        val (viewModel, liveData) = initViewModel(testScheduler)
        val expected = listOf(
            NoteUIModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00")
        )
        viewModel.createNote("TestHeader 1", "TestBody 1").join()
        viewModel.showNoteList().join()

        val actual = liveData.notesList

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun removing_note_test() = runTest {
        val (viewModel, liveData) = initViewModel(testScheduler)

        var expected: List<NoteUIModel> = listOf(
            NoteUIModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00")
        )
        viewModel.createNote("TestHeader 1", "TestBody 1").join()

        viewModel.showNoteList().join()
        var actual = liveData.notesList

        assertEquals(expected[0], actual[0])

        viewModel.removeNote(0).join()

        expected = listOf(NoteUIModel.Failed("There are no notes. Create new one"))

        viewModel.showNoteList().join()
        actual = liveData.notesList

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)

        viewModel.undoDeleting().join()

        expected = listOf(
        NoteUIModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00")
        )

        viewModel.showNoteList().join()
        actual = liveData.notesList

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun saving_multiple_notes_test() = runTest {
        val (viewModel, liveData) = initViewModel(testScheduler)
        val expected = listOf(
            NoteUIModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00"),
            NoteUIModel.Success(1, "TestHeader 2", "TestBody 2", "11:11 11.11"),
            NoteUIModel.Success(2, "TestHeader 3", "TestBody 3", "22:22 22.22")
        )
        viewModel.createNote("TestHeader 1", "TestBody 1").join()

        viewModel.showNoteList().join()
        var actual = liveData.notesList

        assertEquals(expected[0], actual[0])

        viewModel.createNote("TestHeader 2", "TestBody 2").join()

        viewModel.showNoteList().join()
        actual = liveData.notesList

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])

        viewModel.createNote("TestHeader 3", "TestBody 3").join()

        viewModel.showNoteList().join()
        actual = liveData.notesList

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])
    }

    @Test
    fun updating_note_test() = runTest {
        val (viewModel, liveData) = initViewModel(testScheduler)
        var expected = listOf(
            NoteUIModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00")
        )
        viewModel.createNote("TestHeader 1", "TestBody 1").join()

        viewModel.showNoteList().join()
        var actual = liveData.notesList

        assertEquals(expected[0], actual[0])

        expected = listOf(
            NoteUIModel.Success(0, "NewTestHeader 1", "NewTestBody 1", "00:00 00.00")
        )

        viewModel.updateNote(0, "NewTestHeader 1", "NewTestBody 1").join()

        viewModel.showNoteList().join()
        actual = liveData.notesList

        assertEquals(expected[0], actual[0])
    }


}