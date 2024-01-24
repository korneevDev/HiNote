package github.mik0war.hinote.domain

import github.mik0war.hinote.R
import github.mik0war.hinote.core.StringResourceManager
import github.mik0war.hinote.domain.entity.NoteModel
import java.io.IOException
import javax.inject.Inject

interface ExceptionHandler {
    fun handle(e: Exception): NoteModel.Failed

    class Base @Inject constructor(private val stringResourceManager: StringResourceManager) : ExceptionHandler {
        override fun handle(e: Exception): NoteModel.Failed {

            return when (e) {
                is NoNotesException -> NoteModel.Failed(
                    stringResourceManager.getString(R.string.noNotesExceptionMessage)
                )
                else -> NoteModel.Failed(
                    stringResourceManager.getString(R.string.defaultExceptionMessage)
                )
            }
        }
    }
}

class NoNotesException : IOException()