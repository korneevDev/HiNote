package github.mik0war.hinote.data

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.core.data.TestNoteDAO
import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.data.entity.NoteDataModel
import github.mik0war.hinote.domain.NoNotesException
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseCacheDataSource {
    @Test
    fun getting_empty_list_test() = runTest{
        val cacheDataSource = CacheDataSource.Base(MapperParametrised.ToDataModel(), TestNoteDAO())
        try{
            cacheDataSource.getNotesList()
        } catch (e: Exception){
            assert(e is NoNotesException)
        }
    }

    @Test
    fun saving_note_test() = runTest {
        val cacheDataSource = CacheDataSource.Base(MapperParametrised.ToDataModel(), TestNoteDAO())
        val expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        )
        cacheDataSource.save("TestHeader 1", "TestBody 1", "23:03 14.01")

        val actual = cacheDataSource.getNotesList()

        assert(expected[0] == actual[0])
    }

    @Test
    fun removing_note_test() = runTest {
        val cacheDataSource = CacheDataSource.Base(MapperParametrised.ToDataModel(), TestNoteDAO())
        val expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        )
        cacheDataSource.save("TestHeader 1", "TestBody 1", "23:03 14.01")

        val actual = cacheDataSource.getNotesList()

        assert(expected[0] == actual[0])

        val expectedDeletedNote = NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        val actualDeletedNote = cacheDataSource.remove(0)

        assert(expectedDeletedNote == actualDeletedNote)

        try {
            cacheDataSource.getNotesList()
        } catch (e: Exception) {
            assert(e is NoNotesException)
        }
    }

    @Test
    fun saving_multiple_notes_test() = runTest {
        val cacheDataSource = CacheDataSource.Base(MapperParametrised.ToDataModel(), TestNoteDAO())
        val expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01"),
            NoteDataModel(0, "TestHeader 2", "TestBody 2", "23:04 14.01"),
            NoteDataModel(0, "TestHeader 3", "TestBody 3", "23:05 14.01")
        )
        cacheDataSource.save(NoteDataModel(0,"TestHeader 1", "TestBody 1", "23:03 14.01"))

        var actual = cacheDataSource.getNotesList()

        assertEquals(expected[0], actual[0])

        cacheDataSource.save("TestHeader 2", "TestBody 2", "23:04 14.01")

        actual = cacheDataSource.getNotesList()

        for(i: Int in actual.indices)
            assertEquals(expected[i], actual[i])

        cacheDataSource.save("TestHeader 3", "TestBody 3", "23:05 14.01")

        actual = cacheDataSource.getNotesList()

        for(i: Int in actual.indices)
            assert(expected[i] == actual[i])
    }

    @Test
    fun updating_note_test() = runTest {
        val cacheDataSource = CacheDataSource.Base(MapperParametrised.ToDataModel(), TestNoteDAO())
        var expected = listOf(
            NoteDataModel(0, "TestHeader 1", "TestBody 1", "23:03 14.01")
        )
        cacheDataSource.save("TestHeader 1", "TestBody 1", "23:03 14.01")

        var actual = cacheDataSource.getNotesList()

        assert(expected[0] == actual[0])

        expected = listOf(
            NoteDataModel(0, "NewTestHeader 1", "NewTestBody 1", "23:03 14.01")
        )

        cacheDataSource.update(0, "NewTestHeader 1", "NewTestBody 1")

        actual = cacheDataSource.getNotesList()

        assertEquals(expected[0], actual[0])
    }

}