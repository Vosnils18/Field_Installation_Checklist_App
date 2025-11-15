package eu.foxbms.installationchecklist.data.repository

import eu.foxbms.installationchecklist.data.local.PhotoDao
import eu.foxbms.installationchecklist.data.local.PhotoEntity
import kotlinx.coroutines.flow.Flow

class PhotoRepository(private val photoDao: PhotoDao) {
    fun getPhotosByEntity(entityId: Int, entityType: String): Flow<List<PhotoEntity>> =
        photoDao.getPhotosByEntity(entityId, entityType)

    suspend fun insertPhoto(photo: PhotoEntity) = photoDao.insertPhoto(photo)

    suspend fun deletePhoto(id: Int) = photoDao.deletePhoto(id)
}
