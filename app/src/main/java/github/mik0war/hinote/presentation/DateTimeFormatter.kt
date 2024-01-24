package github.mik0war.hinote.presentation

import github.mik0war.hinote.R
import github.mik0war.hinote.core.StringResourceManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

interface DateTimeFormatter {
    fun formatDate(dateTime: Long, lastEditedDateTime: Long?): String

    abstract class Abstract: DateTimeFormatter {

        protected fun formatDate(pattern: String, dateTime: Long): String {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            return sdf.format(Date(dateTime))
        }
    }

    class Base @Inject constructor(): Abstract() {
        override fun formatDate(dateTime: Long, lastEditedDateTime: Long?) =
            formatDate("hh:mm:ss dd-MM", dateTime)
    }

    class LastEdited @Inject constructor(): Abstract() {
        override fun formatDate(dateTime: Long, lastEditedDateTime: Long?) =
            formatDate("hh:mm:ss dd-MM", lastEditedDateTime ?: dateTime)
    }

    class LastEditedWithLabel @Inject constructor(
        private val stringResourceManager: StringResourceManager
        ): Abstract() {
        override fun formatDate(dateTime: Long, lastEditedDateTime: Long?) =
            (if (lastEditedDateTime == null)
                "" else stringResourceManager.getString(R.string.editedNoteMessage)) + " " +
                    formatDate("hh:mm:ss dd-MM",lastEditedDateTime ?: dateTime)
    }
}