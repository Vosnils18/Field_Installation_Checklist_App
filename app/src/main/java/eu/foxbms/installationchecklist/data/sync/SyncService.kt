package eu.foxbms.installationchecklist.data.sync

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import eu.foxbms.installationchecklist.data.local.Cabinet
import eu.foxbms.installationchecklist.data.local.PhotoEntity
import eu.foxbms.installationchecklist.data.local.Project
import eu.foxbms.installationchecklist.data.remote.FoxBmsApiService
import eu.foxbms.installationchecklist.data.remote.dto.CabinetDto
import eu.foxbms.installationchecklist.data.remote.dto.PhotoDto
import eu.foxbms.installationchecklist.data.remote.dto.ProjectDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncService @Inject constructor(
    private val apiService: FoxBmsApiService,
    @ApplicationContext private val context: Context
) {

    suspend fun syncProject(project: Project) {
        withContext(Dispatchers.IO) {
            try {
                val dto = ProjectDto(
                    id = if (project.id == 0L) null else project.id,
                    name = project.name,
                    description = project.description,
                    mainContact = project.mainContact,
                    createdAt = project.createdAt,
                    updatedAt = project.updatedAt
                )

                if (project.id == 0L) {
                    apiService.createProject(dto)
                } else {
                    apiService.updateProject(dto)
                }
                Log.d("SyncService", "Project synced: ${project.name}")
            } catch (e: Exception) {
                Log.e("SyncService", "Failed to sync project", e)
            }
        }
    }

    suspend fun syncCabinet(cabinet: Cabinet) {
        withContext(Dispatchers.IO) {
            try {
                val dto = CabinetDto(
                    id = if (cabinet.id == 0L) null else cabinet.id,
                    projectId = cabinet.projectId,
                    voltage = cabinet.voltage,
                    frequency = cabinet.frequency,
                    current = cabinet.current,
                    siteConditions = cabinet.siteConditions,
                    material = cabinet.material,
                    colour = cabinet.colour,
                    ingressProtection = cabinet.ingressProtection,
                    mounting = cabinet.mounting,
                    cabinetAccessories = cabinet.cabinetAccessories,
                    cableEntryPosition = cabinet.cableEntryPosition,
                    cableEntryType = cabinet.cableEntryType,
                    location = cabinet.location,
                    finalComments = cabinet.finalComments,
                    width = cabinet.width,
                    height = cabinet.height,
                    depth = cabinet.depth,
                    numberOfDoors = cabinet.numberOfDoors,
                    doorSwingDirection = cabinet.doorSwingDirection,
                    intakeGrillePosition = cabinet.intakeGrillePosition,
                    exhaustGrillePosition = cabinet.exhaustGrillePosition,
                    freeSpaceRight = cabinet.freeSpaceRight,
                    freeSpaceLeft = cabinet.freeSpaceLeft,
                    freeSpaceTop = cabinet.freeSpaceTop,
                    freeSpaceBottom = cabinet.freeSpaceBottom
                )

                if (cabinet.id == 0L) {
                    apiService.createCabinet(dto)
                } else {
                    apiService.updateCabinet(dto)
                }
                Log.d("SyncService", "Cabinet synced: ${cabinet.id}")
            } catch (e: Exception) {
                Log.e("SyncService", "Failed to sync cabinet", e)
            }
        }
    }

    suspend fun syncPhoto(photo: PhotoEntity) {
        withContext(Dispatchers.IO) {
            try {
                val base64Image = uriToBase64(Uri.parse(photo.uri))

                val dto = PhotoDto(
                    id = if (photo.id == 0) null else photo.id,
                    entityId = photo.entityId,
                    entityType = photo.entityType,
                    imageBase64 = base64Image,
                    createdAt = photo.createdAt
                )

                apiService.uploadPhoto(dto)
                Log.d("SyncService", "Photo synced: ${photo.id}")
            } catch (e: Exception) {
                Log.e("SyncService", "Failed to sync photo", e)
            }
        }
    }

    private fun uriToBase64(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
}