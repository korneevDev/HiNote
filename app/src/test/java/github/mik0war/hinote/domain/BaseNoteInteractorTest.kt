package github.mik0war.hinote.domain

import github.mik0war.hinote.core.data.TestCacheDataSource
import github.mik0war.hinote.core.domain.TestCurrentDateTime
import github.mik0war.hinote.core.domain.TestResourceManager
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.entity.NoteModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseNoteInteractorTest {
    private fun initInteractor(): NoteInteractor.Base {
        val repository = NoteRepository.Base(TestCacheDataSource())
        return NoteInteractor.Base(
            repository,
            ExceptionHandler.Base(
                TestResourceManager()
            ),
            TestCurrentDateTime()
        )
    }

    @Test
    fun getting_empty_list_test() = runTest{
        val interactor = initInteractor()
        val expected = listOf(NoteModel.Failed("There are no notes. Create new one"))
        val actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun saving_note_test()= runTest() {
        val interactor = initInteractor()
        val expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00")
        )
        interactor.addNote("TestHeader 1", "TestBody 1")

        val actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun removing_note_test()= runTest {
        val interactor = initInteractor()
        var expected: List<NoteModel> = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00")
        )
        interactor.addNote("TestHeader 1", "TestBody 1")

        var actual = interactor.getNoteList()

        assert(expected[0] == actual[0])

        interactor.removeNote(0)

        expected = listOf(NoteModel.Failed("There are no notes. Create new one"))
        actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)

        expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00")
        )
        interactor.undoDeletingNote()
        actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun saving_multiple_notes_test() = runTest {
        val interactor = initInteractor()
        val expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00"),
            NoteModel.Success(1, "TestHeader 2", "TestBody 2", "11:11 11.11"),
            NoteModel.Success(2, "TestHeader 3", "TestBody 3", "22:22 22.22")
        )
        interactor.addNote("TestHeader 1", "TestBody 1")

        var actual = interactor.getNoteList()

        assert(expected[0] == actual[0])

        interactor.addNote("TestHeader 2", "TestBody 2")

        actual = interactor.getNoteList()

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])

        interactor.addNote("TestHeader 3", "TestBody 3")

        actual = interactor.getNoteList()

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])
    }

    @Test
    fun updating_note_test() = runTest {
        val interactor = initInteractor()
        var expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", "00:00 00.00")
        )
        interactor.addNote("TestHeader 1", "TestBody 1")

        var actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])

        expected = listOf(
            NoteModel.Success(0, "NewTestHeader 1", "NewTestBody 1", "00:00 00.00")
        )

        interactor.updateNote(0, "NewTestHeader 1", "NewTestBody 1")

        actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
    }
}