package eu.foxbms.installationchecklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import eu.foxbms.installationchecklist.data.local.Cabinet
import eu.foxbms.installationchecklist.data.repository.CabinetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CabinetViewModel(private val cabinetRepository: CabinetRepository) : ViewModel() {
    fun insertCabinet(cabinet: Cabinet) {
        viewModelScope.launch {
            cabinetRepository.insertCabinet(cabinet)
        }
    }

    fun updateCabinet(cabinet: Cabinet) {
        viewModelScope.launch {
            cabinetRepository.updateCabinet(cabinet)
        }
    }

    fun getCabinetsForProject(projectId: Long): Flow<List<Cabinet>> {
        return cabinetRepository.getCabinetsForProject(projectId)
    }

    fun getCabinet(cabinetId: Long): Flow<Cabinet?> {
        return cabinetRepository.getCabinet(cabinetId)
    }
}
