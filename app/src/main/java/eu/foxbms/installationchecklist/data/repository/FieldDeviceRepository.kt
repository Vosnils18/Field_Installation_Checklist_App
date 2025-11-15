package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.FieldDeviceDao
import eu.foxbms.installationchecklist.data.local.FieldDeviceEntity
import kotlinx.coroutines.flow.Flow

class FieldDeviceRepository(private val fieldDeviceDao: FieldDeviceDao) {
    fun getFieldDevicesByCabinetId(cabinetId: Int): Flow<List<FieldDeviceEntity>> =
        fieldDeviceDao.getFieldDevicesByCabinetId(cabinetId)

    fun getFieldDeviceById(id: Int): Flow<FieldDeviceEntity?> =
        fieldDeviceDao.getFieldDeviceById(id)

    suspend fun insertFieldDevice(fieldDevice: FieldDeviceEntity) =
        fieldDeviceDao.insertFieldDevice(fieldDevice)

    suspend fun updateFieldDevice(fieldDevice: FieldDeviceEntity) =
        fieldDeviceDao.updateFieldDevice(fieldDevice)

    suspend fun deleteFieldDevice(id: Int) = fieldDeviceDao.deleteFieldDevice(id)
}
