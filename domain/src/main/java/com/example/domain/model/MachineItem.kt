package com.example.domain.model

import java.util.Date

data class MachineItem(
    val id: Int,
    val machineName: String,
    val machineType: String,
    val machineQRCodeNumber: Int,
    val lastMaintainedDate: Date,
    val images: List<String>? = null
)
