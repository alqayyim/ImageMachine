package com.example.data

import com.example.core.Mapper
import com.example.data.entity.MachineItemEntity
import com.example.domain.model.MachineItem

class MachineEntityToMachineMapper : Mapper<MachineItemEntity, MachineItem> {

    override fun to(t: MachineItemEntity): MachineItem {
        return MachineItem(
            id = t.id,
            machineName = t.name,
            machineType = t.type,
            machineQRCodeNumber = t.qrCodeNumber,
            lastMaintainedDate = t.lastMaintainedDate,
            images = t.images?.map { it }
        )
    }
}
