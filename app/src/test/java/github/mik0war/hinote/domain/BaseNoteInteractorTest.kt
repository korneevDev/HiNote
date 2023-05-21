package github.mik0war.hinote.domain

import github.mik0war.hinote.core.data.TestCacheDataSource
import github.mik0war.hinote.core.domain.TestCurrentDateTime
import github.mik0war.hinote.core.domain.TestResourceManager
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.model.NoteModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
    fun getting_empty_list_test(): Unit = runTest{
        val interactor = initInteractor()
        val expected = listOf(NoteModel.Failed("There are no notes. Create new one"))
        val actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun saving_note_test(): Unit = runBlocking {
        val interactor = initInteractor()
        val expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        )
        interactor.addNote("TestHeader 1", "TestBody 1", "23:03 14.01")

        val actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun removing_note_test(): Unit = runBlocking {
        val interactor = initInteractor()
        var expected: List<NoteModel> = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        )
        interactor.addNote("TestHeader 1", "TestBody 1", "23:03 14.01")

        var actual = interactor.getNoteList()

        assert(expected[0] == actual[0])

        val expectedDeletedNote = NoteModel.Success(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        val actualDeletedNote = interactor.removeNote(0)

        assert(expectedDeletedNote == actualDeletedNote)

        expected = listOf(NoteModel.Failed("There are no notes. Create new one"))
        actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
        assertEquals(1, actual.size)
    }

    @Test
    fun saving_multiple_notes_test(): Unit = runBlocking {
        val interactor = initInteractor()
        val expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", "23:03 14.01"),
            NoteModel.Success(1, "TestHeader 2", "TestBody 2", "23:04 14.01"),
            NoteModel.Success(2, "TestHeader 3", "TestBody 3", "23:05 14.01")
        )
        interactor.addNote("TestHeader 1", "TestBody 1", "23:03 14.01")

        var actual = interactor.getNoteList()

        assert(expected[0] == actual[0])

        interactor.addNote("TestHeader 2", "TestBody 2", "23:04 14.01")

        actual = interactor.getNoteList()

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])

        interactor.addNote("TestHeader 3", "TestBody 3", "23:05 14.01")

        actual = interactor.getNoteList()

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])
    }

    @Test
    fun updating_note_test(): Unit = runBlocking {
        val interactor = initInteractor()
        var expected = listOf(
            NoteModel.Success(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        )
        interactor.addNote("TestHeader 1", "TestBody 1", "23:03 14.01")

        var actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])

        expected = listOf(
            NoteModel.Success(0, "NewTestHeader 1", "NewTestBody 1", "23:03 14.01")
        )

        interactor.updateNote(0, "NewTestHeader 1", "NewTestBody 1")

        actual = interactor.getNoteList()

        assertEquals(expected[0], actual[0])
    }
}