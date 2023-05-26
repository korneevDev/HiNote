package github.mik0war.hinote.domain

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

interface ResourceManager {
    fun getString(@StringRes resId: Int) : String

    class Base @Inject constructor(private val context: Context) : ResourceManager {
        override fun getString(resId: Int) = context.getString(resId)
    }
}