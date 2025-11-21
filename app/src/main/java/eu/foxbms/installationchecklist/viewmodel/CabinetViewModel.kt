package eu.foxbms.installationchecklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import eu.foxbms.installationchecklist.data.local.Cabinet
import eu.foxbms.installationchecklist.data.repository.CabinetRepository
import javax.inject.Inject

@HiltViewModel
class CabinetViewModel @Inject constructor(
    private val cabinetRepository: CabinetRepository
) : ViewModel() {
    fun getCabinetsByProjectId(projectId: Int) = cabinetRepository.getCabinetsByProjectId(projectId)

    fun insertCabinet(cabinet: Cabinet) = viewModelScope.launch {
        cabinetRepository.insertCabinet(cabinet)
    }

    fun updateCabinet(cabinet: Cabinet) = viewModelScope.launch {
        cabinetRepository.updateCabinet(cabinet)
    }

    fun deleteCabinet(id: Int) = viewModelScope.launch {
        cabinetRepository.deleteCabinet(id)
    }
}
