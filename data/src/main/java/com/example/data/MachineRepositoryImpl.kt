package com.example.data

import com.example.core.buildFlow
import com.example.core.data.Resource
import com.example.core.mapTo
import com.example.data.entity.MachineDao
import com.example.domain.model.MachineItem
import com.example.domain.repository.MachineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MachineRepositoryImpl(
    private val machineDao: MachineDao,
    private val machineMapper: MachineMapper
) : MachineRepository {
    override suspend fun getMachineList(): Flow<Resource<List<MachineItem>>> {
        return flow {
            val localDb = machineDao.getAllMachine().map {
                it.mapTo(machineMapper)
            }
            val response = Resource.Success(localDb.ifEmpty { getDummyData() })
            emit(response)
        }.buildFlow()
    }

    // mock fetch data from API/Backend
    private fun getDummyData(): List<MachineItem> {
        val MACHINE_1 = MachineItem(
            11111,
            "MachineOne",
            "MachineType1",
            12337,
            "April 12 2023"
        )

        val MACHINE_2 = MachineItem(
            22222,
            "MachineTwo",
            "MachineType2",
            12338,
            "May 7 2023"
        )

        val MACHINE_3 = MachineItem(
            33333,
            "MachineThree",
            "MachineType3",
            12339,
            "January 1 2023"
        )

        val MACHINE_4 = MachineItem(
            44444,
            "MachineFour",
            "MachineType4",
            12340,
            "December 4 2022"
        )

        val MACHINE_5 = MachineItem(
            55555,
            "MachineFive",
            "MachineType5",
            12341,
            "March 25 2023"
        )

        val MACHINE_6 = MachineItem(
            66666,
            "MachineSix",
            "MachineType5",
            12341,
            "March 25 2023"
        )

        return listOf(MACHINE_1, MACHINE_2, MACHINE_3, MACHINE_4, MACHINE_5, MACHINE_6)
    }
}