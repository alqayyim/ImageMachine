package com.example.data

import com.example.core.Mapper
import com.example.data.entity.MachineItemEntity
import com.example.domain.model.MachineItem

class MachineToMachineEntityMapper : Mapper<MachineItem, MachineItemEntity> {

    override fun to(t: MachineItem): MachineItemEntity {
        return MachineItemEntity(
            id = t.id,
            name = t.machineName,
            type = t.machineType,
            qrCodeNumber = t.machineQRCodeNumber,
            lastMaintainedDate = t.lastMaintainedDate,
            images = t.images?.map { it }
        )
    }
}
