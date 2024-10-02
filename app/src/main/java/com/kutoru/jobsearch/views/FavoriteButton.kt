package com.kutoru.jobsearch.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.kutoru.jobsearch.R

class FavoriteButton : AppCompatImageButton {
    constructor(context: Context): super(context)
    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)

    var isFavorite = false
        set(value) {
            if (value) {
                this.setImageResource(R.drawable.icon_heart_filled)
            } else {
                this.setImageResource(R.drawable.icon_heart)
            }

            field = value
        }
}
