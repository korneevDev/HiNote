package github.mik0war.hinote

import github.mik0war.hinote.core.MockCacheDataSource
import github.mik0war.hinote.core.MockCurrentDateTime
import github.mik0war.hinote.core.MockResourceManager
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.domain.model.NoteModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class BaseNoteInteractorTest {

    @Test
    fun test() = runBlocking {
        val expected = listOf(
            NoteModel.Success(0, "test Header 1", "Test Body 1", "00:00:00"),
            NoteModel.Success(1, "test Header 2", "Test Body 2", "11:11:11")
        )
        val repository = NoteRepository.Base(MockCacheDataSource())
        val interactor = NoteInteractor.Base(
            repository,
            ExceptionHandler.Base(
                MockResourceManager()
            ),
            MockCurrentDateTime()
        )

        val expectedError = NoteModel.Failed("No notes")
        var actual = interactor.getNoteList()

        assertEquals(actual[0], expectedError)

        interactor.addNote("test Header 1", "Test Body 1")
        interactor.addNote("test Header 2", "Test Body 2")

        actual = interactor.getNoteList()

        for (i: Int in expected.indices) {
            assertEquals(expected[i], actual[i])
        }

        interactor.removeNote(0)

        actual = interactor.getNoteList()

        assertEquals(1, actual.size)
        assertEquals(expected[1], actual[0])

        interactor.removeNote(0)

        actual = interactor.getNoteList()

        assertEquals(expectedError, actual[0])
    }
}