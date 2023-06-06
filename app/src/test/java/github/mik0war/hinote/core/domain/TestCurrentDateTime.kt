package github.mik0war.hinote.core.domain

import github.mik0war.hinote.domain.CurrentDateTime

class TestCurrentDateTime: CurrentDateTime {
    private var i = 0
    override fun getCurrentTime() = (i++).toLong()
}