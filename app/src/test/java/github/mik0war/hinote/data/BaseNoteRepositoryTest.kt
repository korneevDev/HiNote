package github.mik0war.hinote.data

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.core.data.TestNoteDAO
import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.data.entity.NoteDataModel
import github.mik0war.hinote.domain.NoNotesException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseNoteRepositoryTest {
    private fun initRepo(
        testScheduler : TestCoroutineScheduler
    ): NoteRepository = NoteRepository.Base(
            CacheDataSource.Base(
                MapperParametrised.ToDataModel(),
                TestNoteDAO()
            ), StandardTestDispatcher(testScheduler)
        )
    @Test
    fun getting_empty_list_test() = runTest{
        val repository = initRepo(testScheduler)
        try{
            repository.getNotesList()
        } catch (e: Exception){
            assertEquals(e.javaClass,  NoNotesException::class.java)
        }
    }

    @Test
    fun saving_note_test() = runTest {
        val repository = initRepo(testScheduler)
        val expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", 1, null)
        )
        repository.saveNote("TestHeader 1", "TestBody 1", 1)

        val actual = repository.getNotesList()

        assert(expected[0] == actual[0])
    }

    @Test
    fun removing_and_undo_note_test() = runTest {
        val repository = initRepo(testScheduler)
        val expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", 1, null)
        )
        repository.saveNote("TestHeader 1", "TestBody 1", 1)

        var actual = repository.getNotesList()

        assertEquals(expected[0], actual[0])

        repository.removeNote(0)

        try {
            repository.getNotesList()
        } catch (e: Exception) {
            assert(e is NoNotesException)
        }

        repository.saveCachedNote()

        actual = repository.getNotesList()
        assertEquals(expected[0], actual[0])
    }

    @Test
    fun saving_multiple_notes_test() = runTest {
        val repository = initRepo(testScheduler)
        val expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", 1, null),
            NoteDataModel(0, "TestHeader 2", "TestBody 2", 2, null),
            NoteDataModel(0, "TestHeader 3", "TestBody 3", 3, null)
        )
        repository.saveNote("TestHeader 1", "TestBody 1", 1)

        var actual = repository.getNotesList()

        assertEquals(expected[0], actual[0])

        repository.saveNote("TestHeader 2", "TestBody 2", 2)

        actual = repository.getNotesList()

        for(i: Int in actual.indices)
            assertEquals(expected[i], actual[i])

        repository.saveNote("TestHeader 3", "TestBody 3", 3)

        actual = repository.getNotesList()

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])
    }

    @Test
    fun updating_note_test() = runTest {
        val repository = initRepo(testScheduler)
        var expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", 1, null)
        )
        repository.saveNote("TestHeader 1", "TestBody 1", 1)

        var actual = repository.getNotesList()

        assert(expected[0] == actual[0])

        expected = listOf(
            NoteDataModel(0, "NewTestHeader 1", "NewTestBody 1", 1, 2)
        )

        repository.updateNote(0, "NewTestHeader 1", "NewTestBody 1", 2)

        actual = repository.getNotesList()

        assert(expected[0] == actual[0])
    }
}