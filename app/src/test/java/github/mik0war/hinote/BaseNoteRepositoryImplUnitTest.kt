package github.mik0war.hinote

import github.mik0war.hinote.data.BaseNoteRepositoryImpl
import github.mik0war.hinote.data.NoteDAO
import github.mik0war.hinote.core.TestCacheDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

class BaseNoteRepositoryImplUnitTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest{
        val expected = listOf(
            NoteDAO(0, "test Header 1", "Test Body 1"),
            NoteDAO(1, "test Header 2", "Test Body 2")
        )
        val repository = BaseNoteRepositoryImpl(TestCacheDataSource())

        repository.saveNode(NoteDAO(0, "test Header 1", "Test Body 1"))
        repository.saveNode(NoteDAO(1, "test Header 2", "Test Body 2"))
        var actual = repository.getNotesList()

        for (i: Int in expected.indices) {
            assert(expected[i].id == actual[i].id)
            assert(expected[i].header == actual[i].header)
            assert(expected[i].body == actual[i].body)
        }

        repository.removeNote(1)

        actual = repository.getNotesList()

        assert(actual.size == 1)
    }
}