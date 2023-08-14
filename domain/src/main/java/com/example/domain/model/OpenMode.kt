package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class OpenMode : Parcelable {
    NEW,
    EDIT
}