package com.example.cryptocurrency.mvvm.controls

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

import androidx.appcompat.widget.AppCompatTextView


class TextViewRegular : AppCompatTextView {
    constructor(context: Context?) : super(context!!) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    fun init(attributeSet: AttributeSet?) {
        this.typeface = Typeface.createFromAsset(resources.assets, "fonts/Heebo-Regular.ttf")
    }
}