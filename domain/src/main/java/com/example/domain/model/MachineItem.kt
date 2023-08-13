package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MachineItem(
    val id: Int,
    val machineName: String,
    val machineType: String,
    val machineQRCodeNumber: Int,
    val lastMaintainedDate: String,
    val images: List<String>? = null
): Parcelable
