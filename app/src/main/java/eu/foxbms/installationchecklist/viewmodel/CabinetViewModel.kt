package eu.foxbms.installationchecklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import eu.foxbms.installationchecklist.data.local.Cabinet
import eu.foxbms.installationchecklist.data.repository.CabinetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CabinetViewModel @Inject constructor(
    private val cabinetRepository: CabinetRepository
) : ViewModel() {

    fun getCabinetsForProject(projectId: Long): Flow<List<Cabinet>> =
        cabinetRepository.getCabinetsForProject(projectId)

    fun getCabinet(cabinetId: Long): Flow<Cabinet?> =
        cabinetRepository.getCabinetById(cabinetId)

    suspend fun insertCabinet(cabinet: Cabinet): Long {
        return cabinetRepository.insertCabinet(cabinet)
    }

    fun updateCabinet(cabinet: Cabinet) = viewModelScope.launch {
        cabinetRepository.updateCabinet(cabinet)
    }

    fun deleteCabinet(id: Long) = viewModelScope.launch {
        cabinetRepository.deleteCabinet(id)
    }
}