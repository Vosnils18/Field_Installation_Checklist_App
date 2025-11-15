package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.CabinetDao
import eu.foxbms.installationchecklist.data.local.CabinetEntity
import kotlinx.coroutines.flow.Flow

class CabinetRepository(private val cabinetDao: CabinetDao) {
    fun getCabinetsByProjectId(projectId: Int): Flow<List<CabinetEntity>> =
        cabinetDao.getCabinetsByProjectId(projectId)

    fun getCabinetById(id: Int): Flow<CabinetEntity?> = cabinetDao.getCabinetById(id)

    suspend fun insertCabinet(cabinet: CabinetEntity) = cabinetDao.insertCabinet(cabinet)

    suspend fun updateCabinet(cabinet: CabinetEntity) = cabinetDao.updateCabinet(cabinet)

    suspend fun deleteCabinet(id: Int) = cabinetDao.deleteCabinet(id)
}
