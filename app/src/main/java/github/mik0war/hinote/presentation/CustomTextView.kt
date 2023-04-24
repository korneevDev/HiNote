package github.mik0war.hinote.presentation

import android.content.Context
import android.util.AttributeSet

class CustomTextViewImpl : CustomTextView, androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(text: String) {
        this.text = text
    }
}

interface CustomTextView{
    fun show(text: String)
}