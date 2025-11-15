package eu.foxbms.installationchecklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import eu.foxbms.installationchecklist.data.local.CabinetEntity
import eu.foxbms.installationchecklist.data.repository.CabinetRepository
import javax.inject.Inject

@HiltViewModel
class CabinetViewModel @Inject constructor(
    private val cabinetRepository: CabinetRepository
) : ViewModel() {
    fun getCabinetsByProjectId(projectId: Int) = cabinetRepository.getCabinetsByProjectId(projectId)

    fun insertCabinet(cabinet: CabinetEntity) = viewModelScope.launch {
        cabinetRepository.insertCabinet(cabinet)
    }

    fun updateCabinet(cabinet: CabinetEntity) = viewModelScope.launch {
        cabinetRepository.updateCabinet(cabinet)
    }

    fun deleteCabinet(id: Int) = viewModelScope.launch {
        cabinetRepository.deleteCabinet(id)
    }
}
