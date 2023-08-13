package com.example.domain.usecase

import com.example.core.FlowUseCase
import com.example.core.data.Resource
import com.example.domain.model.MachineItem
import com.example.domain.repository.MachineRepository
import kotlinx.coroutines.flow.Flow

class GetMachineListUseCase(private val repo: MachineRepository) : FlowUseCase<Nothing?, List<MachineItem>>() {
    override suspend fun execute(parameters: Nothing?): Flow<Resource<List<MachineItem>>> {
        return repo.getMachineList()
    }
}