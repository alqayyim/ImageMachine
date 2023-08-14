package com.example.core

import android.net.Uri
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible(isShowing: Boolean) {
    visibility = when (isShowing) {
        true -> VISIBLE
        false -> GONE
    }
}

fun visibleMultipleViews(vararg views: View) {
    views.forEach {
        it.visible(true)
    }
}

fun goneMultipleViews(vararg views: View) {
    views.forEach {
        it.visible(false)
    }
}

fun ImageView.loadImage(img: Uri) {
    Glide.with(this).load(img).into(this)
}

fun EditText.isEditable(editable: Boolean) {
    isFocusable = editable
    isFocusableInTouchMode = editable
    isClickable = editable
    isEnabled = editable
}


