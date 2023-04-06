package github.mik0war.hinote

import github.mik0war.hinote.core.MockCacheDataSource
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.data.model.NoteDataModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BaseNoteRepositoryImplUnitTest {

    @Test
    fun test() = runBlocking{
        val expected = listOf(
            NoteDataModel(0, "test Header 1", "Test Body 1", "23-03-23"),
            NoteDataModel(1, "test Header 2", "Test Body 2", "24-04-19")
        )
        val repository = NoteRepository.Base(MockCacheDataSource())

        repository.saveNote(NoteDataModel(0, "test Header 1", "Test Body 1", "23-03-23"))
        repository.saveNote(NoteDataModel(1, "test Header 2", "Test Body 2", "24-04-19"))
        var actual = repository.getNotesList()

        for (i: Int in expected.indices) {
            assert(expected[i] == actual[i])
        }

        repository.removeNote(1)

        actual = repository.getNotesList()

        assert(actual.size == 1)
    }
}