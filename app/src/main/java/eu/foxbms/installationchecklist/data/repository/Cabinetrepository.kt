package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.CabinetDao
import eu.foxbms.installationchecklist.data.local.Cabinet
import kotlinx.coroutines.flow.Flow

// TODO: Change these functions or delete them to conform to the new architecture
class CabinetRepository(private val cabinetDao: CabinetDao) {
    fun getCabinetsByProjectId(projectId: Int): Flow<List<Cabinet>> =
        cabinetDao.getCabinetsByProjectId(projectId)

    fun getCabinetById(id: Int): Flow<Cabinet?> = cabinetDao.getCabinetById(id)

    suspend fun insertCabinet(cabinet: Cabinet) = cabinetDao.insertCabinet(cabinet)

    suspend fun updateCabinet(cabinet: Cabinet) = cabinetDao.updateCabinet(cabinet)

    suspend fun deleteCabinet(id: Int) = cabinetDao.deleteCabinet(id)
}
