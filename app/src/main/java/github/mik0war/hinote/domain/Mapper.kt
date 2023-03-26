package github.mik0war.hinote.domain

import github.mik0war.hinote.presentation.NoteUIModel
import java.util.*

interface Mapper {
    fun map(id: Int, header: String, body: String, date: Date) : NoteUIModel
}