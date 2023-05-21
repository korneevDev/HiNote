package github.mik0war.hinote.domain

import github.mik0war.hinote.R
import github.mik0war.hinote.domain.entity.NoteModel
import java.io.IOException

interface ExceptionHandler {
    fun handle(e: Exception): NoteModel.Failed

    class Base(private val resourceManager: ResourceManager) : ExceptionHandler {
        override fun handle(e: Exception): NoteModel.Failed {

            return when (e) {
                is NoNotesException -> NoteModel.Failed(
                    resourceManager.getString(R.string.noNotesExceptionMessage)
                )
                else -> NoteModel.Failed(
                    resourceManager.getString(R.string.defaultExceptionMessage)
                )
            }
        }
    }
}

class NoNotesException : IOException()