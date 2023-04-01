package github.mik0war.hinote

import github.mik0war.hinote.data.BaseNoteRepositoryImpl
import github.mik0war.hinote.data.NoteDataModel
import github.mik0war.hinote.core.MockCacheDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class BaseNoteRepositoryImplUnitTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest{
        val expected = listOf(
            NoteDataModel(0, "test Header 1", "Test Body 1", Date(2023, 3, 29)),
            NoteDataModel(1, "test Header 2", "Test Body 2", Date(2023, 3, 29))
        )
        val repository = BaseNoteRepositoryImpl(MockCacheDataSource())

        repository.saveNote(NoteDataModel(0, "test Header 1", "Test Body 1", Date(2023, 3, 29)))
        repository.saveNote(NoteDataModel(1, "test Header 2", "Test Body 2", Date(2023, 3, 29)))
        var actual = repository.getNotesList()

        for (i: Int in expected.indices) {
            assert(expected[i] == actual[i])
        }

        repository.removeNote(1)

        actual = repository.getNotesList()

        assert(actual.size == 1)
    }
}