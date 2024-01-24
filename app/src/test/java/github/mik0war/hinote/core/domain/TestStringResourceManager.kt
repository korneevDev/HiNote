package github.mik0war.hinote.core.domain

import github.mik0war.hinote.R
import github.mik0war.hinote.core.StringResourceManager

class TestStringResourceManager : StringResourceManager {
    override fun getString(resId: Int): String =
        if (resId == R.string.noNotesExceptionMessage)
            "There are no notes. Create new one"
        else
            "Unknown exception"
}