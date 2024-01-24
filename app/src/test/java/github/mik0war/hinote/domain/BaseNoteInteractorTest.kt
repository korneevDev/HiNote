package github.mik0war.hinote.domain

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.core.data.TestNoteDAO
import github.mik0war.hinote.core.domain.TestCurrentDateTime
import github.mik0war.hinote.core.domain.TestStringResourceManager
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.domain.entity.NoteModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseNoteInteractorTest {
    private fun initInteractor(
        testCoroutineScheduler: TestCoroutineScheduler
    ): NoteInteractor.Base {
        val repository = NoteRepository.Base(
            CacheDataSource.Base(
                MapperParametrised.ToDataModel(),
                TestNoteDAO()
            ),
            StandardTestDispatcher(testCoroutineScheduler)
        )
        return NoteInteractor.Base(
            repository,
            ExceptionHandler.Base(
                TestStringResourceManager()
            ),
            TestCurrentDateTime()
        )
    }

    @Test
    fun getting_empty_list_test() = runTest{
        val interactor = initInteractor(testScheduler)
        val expected = listOf(NoteModel.Failed("There are no notes. Create new one"))
        val actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun saving_note_test()= runTest() {
        val interactor = initInteractor(testScheduler)
        val expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", 0, null)
        )
        interactor.addNote("TestHeader 1", "TestBody 1")

        val actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun removing_note_test()= runTest {
        val interactor = initInteractor(testScheduler)
        var expected: List<NoteModel> = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", 0, null)
        )
        interactor.addNote("TestHeader 1", "TestBody 1")

        var actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])

        interactor.removeNote(0)

        expected = listOf(NoteModel.Failed("There are no notes. Create new one"))
        actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)

        expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", 0, null)
        )
        interactor.undoDeletingNote()
        actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun saving_multiple_notes_test() = runTest {
        val interactor = initInteractor(testScheduler)
        val expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", 0, null),
            NoteModel.Success(0, "TestHeader 2", "TestBody 2", 1, null),
            NoteModel.Success(0, "TestHeader 3", "TestBody 3", 2, null)
        )
        interactor.addNote("TestHeader 1", "TestBody 1")

        var actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])

        interactor.addNote("TestHeader 2", "TestBody 2")

        actual = interactor.getNoteList()

        for(i: Int in actual.indices)
            assertEquals(expected[i], actual[i])

        interactor.addNote("TestHeader 3", "TestBody 3")

        actual = interactor.getNoteList()

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])
    }

    @Test
    fun updating_note_test() = runTest {
        val interactor = initInteractor(testScheduler)
        var expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", 0, null)
        )
        interactor.addNote("TestHeader 1", "TestBody 1")

        var actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])

        expected = listOf(
            NoteModel.Success(0, "NewTestHeader 1", "NewTestBody 1", 0, 1)
        )

        interactor.updateNote(0, "NewTestHeader 1", "NewTestBody 1")

        actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
    }
}