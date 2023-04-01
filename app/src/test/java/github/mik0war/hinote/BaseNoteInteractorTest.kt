package github.mik0war.hinote

import github.mik0war.hinote.core.*
import github.mik0war.hinote.data.BaseNoteRepositoryImpl
import github.mik0war.hinote.domain.ErrorHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.domain.NoteModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.*

class BaseNoteInteractorTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest{
        val expected = listOf(
            NoteModel.Success(0, "test Header 1", "Test Body 1", Date(29031999)),
            NoteModel.Success(1, "test Header 2", "Test Body 2", Date(29031999))
        )
        val repository = BaseNoteRepositoryImpl(MockCacheDataSource())
        val interactor = NoteInteractor.Base(repository, ErrorHandler.Base(), Mapper.ToNote(), Mapper.ToDataModel())

        val expectedError = NoteModel.Failed("No such notes")
        var actual = interactor.getNoteList()

        assert(actual[0] == expectedError)

        interactor.addNote(0, "test Header 1", "Test Body 1", Date(29031999))
        interactor.addNote(1, "test Header 2", "Test Body 2", Date(29031999))

        actual = interactor.getNoteList()

        for (i: Int in expected.indices) {
            assert(expected[i] == actual[i])
        }

        interactor.removeNote(0)

        actual = interactor.getNoteList()

        assert(actual.size == 1)
    }
}