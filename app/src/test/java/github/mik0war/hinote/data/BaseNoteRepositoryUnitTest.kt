package github.mik0war.hinote.data

import github.mik0war.hinote.core.data.TestCacheDataSource
import github.mik0war.hinote.data.model.NoteDataModel
import github.mik0war.hinote.domain.NoNotesException
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BaseNoteRepositoryUnitTest {
    @Test
    fun getting_empty_list_test(): Unit = runBlocking{
        val repository = NoteRepository.Base(TestCacheDataSource())
        try{
            repository.getNotesList()
        } catch (e: Exception){
            assert(e is NoNotesException)
        }
    }

    @Test
    fun saving_note_test(): Unit = runBlocking {
        val repository = NoteRepository.Base(TestCacheDataSource())
        val expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        )
        repository.saveNote("TestHeader 1", "TestBody 1", "23:03 14.01")

        val actual = repository.getNotesList()

        assert(expected[0] == actual[0])
    }

    @Test
    fun removing_note_test(): Unit = runBlocking {
        val repository = NoteRepository.Base(TestCacheDataSource())
        val expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        )
        repository.saveNote("TestHeader 1", "TestBody 1", "23:03 14.01")

        val actual = repository.getNotesList()

        assert(expected[0] == actual[0])

        val expectedDeletedNote = NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        val actualDeletedNote = repository.removeNote(0)

        assert(expectedDeletedNote == actualDeletedNote)

        try {
            repository.getNotesList()
        } catch (e: Exception) {
            assert(e is NoNotesException)
        }
    }

    @Test
    fun saving_multiple_notes_test(): Unit = runBlocking {
        val repository = NoteRepository.Base(TestCacheDataSource())
        val expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01"),
            NoteDataModel(1, "TestHeader 2", "TestBody 2", "23:04 14.01"),
            NoteDataModel(2, "TestHeader 3", "TestBody 3", "23:05 14.01")
        )
        repository.saveNote("TestHeader 1", "TestBody 1", "23:03 14.01")

        var actual = repository.getNotesList()

        assert(expected[0] == actual[0])

        repository.saveNote("TestHeader 2", "TestBody 2", "23:04 14.01")

        actual = repository.getNotesList()

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])

        repository.saveNote("TestHeader 3", "TestBody 3", "23:05 14.01")

        actual = repository.getNotesList()

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])
    }

    @Test
    fun updating_note_test(): Unit = runBlocking {
        val repository = NoteRepository.Base(TestCacheDataSource())
        var expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        )
        repository.saveNote("TestHeader 1", "TestBody 1", "23:03 14.01")

        var actual = repository.getNotesList()

        assert(expected[0] == actual[0])

        expected = listOf(
            NoteDataModel(0, "NewTestHeader 1", "NewTestBody 1", "23:03 14.01")
        )

        repository.updateNote(0, "NewTestHeader 1", "NewTestBody 1")

        actual = repository.getNotesList()

        assert(expected[0] == actual[0])
    }
}