package eu.foxbms.installationchecklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import eu.foxbms.installationchecklist.data.local.FieldDeviceEntity
import eu.foxbms.installationchecklist.data.repository.FieldDeviceRepository
import javax.inject.Inject

@HiltViewModel
class FieldDeviceViewModel @Inject constructor(
    private val fieldDeviceRepository: FieldDeviceRepository
) : ViewModel() {
    fun getFieldDevicesByCabinetId(cabinetId: Int) =
        fieldDeviceRepository.getFieldDevicesByCabinetId(cabinetId)

    fun insertFieldDevice(fieldDevice: FieldDeviceEntity) = viewModelScope.launch {
        fieldDeviceRepository.insertFieldDevice(fieldDevice)
    }

    fun updateFieldDevice(fieldDevice: FieldDeviceEntity) = viewModelScope.launch {
        fieldDeviceRepository.updateFieldDevice(fieldDevice)
    }

    fun deleteFieldDevice(id: Int) = viewModelScope.launch {
        fieldDeviceRepository.deleteFieldDevice(id)
    }
}
