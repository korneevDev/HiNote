package github.mik0war.hinote.core

import github.mik0war.hinote.domain.ResourceManager

class MockResourceManager : ResourceManager {
    override fun getString(resId: Int): String = "No notes"
}