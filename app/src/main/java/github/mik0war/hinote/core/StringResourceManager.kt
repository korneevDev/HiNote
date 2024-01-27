package github.mik0war.hinote.core

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface StringResourceManager {
    fun getString(@StringRes resId: Int) : String

    class Base @Inject constructor(@ApplicationContext private val context: Context) : StringResourceManager {
        override fun getString(resId: Int) = context.getString(resId)
    }
}

interface ColorResourceManager {
    fun getColor(@ColorRes resId: Int) : Int

    class Base @Inject constructor(private val context: Context) : ColorResourceManager {
        override fun getColor(resId: Int): Int = context.getColor(resId)
    }
}