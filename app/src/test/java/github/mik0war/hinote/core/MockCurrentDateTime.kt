package github.mik0war.hinote.core

import github.mik0war.hinote.domain.CurrentDateTime

class MockCurrentDateTime: CurrentDateTime {
    private var i = 0
    override fun getCurrentTime() = "$i$i:$i$i:$i$i".also { i++ }
}