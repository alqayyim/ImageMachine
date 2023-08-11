package com.example.data

import com.example.core.data.Resource
import com.example.domain.model.MachineItem
import com.example.domain.repository.MachineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

class MachineRepositoryImpl : MachineRepository {
    override suspend fun getMachineList(): Flow<Resource<List<MachineItem>>> {
        // MOCK FETCH DATA SUCCESS FROM API SERVICE
        return flow {
            val response = Resource.Success(getDummyData())
            emit(response)
        }
    }

    private fun getDummyData(): List<MachineItem> {
        val MACHINE_1 = MachineItem(
            1,
            "MachineOne",
            "MachineType1",
            12337,
            Date()
        )

        val MACHINE_2 = MachineItem(
            2,
            "MachineTwo",
            "MachineType2",
            12338,
            Date()
        )

        val MACHINE_3 = MachineItem(
            3,
            "MachineThree",
            "MachineType3",
            12339,
            Date()
        )

        val MACHINE_4 = MachineItem(
            4,
            "MachineFour",
            "MachineType4",
            12340,
            Date()
        )

        val MACHINE_5 = MachineItem(
            5,
            "MachineFive",
            "MachineType5",
            12341,
            Date()
        )

        return listOf(MACHINE_1, MACHINE_2, MACHINE_3, MACHINE_4, MACHINE_5)
    }
}