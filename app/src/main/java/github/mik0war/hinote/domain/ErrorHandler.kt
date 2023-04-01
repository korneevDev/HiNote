package github.mik0war.hinote.domain

import java.io.IOException

interface ErrorHandler {
    fun handle(e: Exception): NoteModel.Failed

    class Base : ErrorHandler {
        override fun handle(e: Exception): NoteModel.Failed {
            return when(e){
                is NoNotesException -> NoteModel.Failed(e.message ?: "No such notes") //TODO replace with @string
                else -> NoteModel.Failed(e.message ?: "Unknown error")
            }
        }
    }
}

class NoNotesException : IOException()