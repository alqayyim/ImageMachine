package com.example.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "machine_table")
data class MachineItemEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val type: String,
    val qrCodeNumber: Int,
    val lastMaintainedDate: String,
    val images: List<String>? = null
)