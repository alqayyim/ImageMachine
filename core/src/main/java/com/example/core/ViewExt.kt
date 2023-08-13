package com.example.core

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

/**
 * @author asadurrahman.qayyim
 */

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


