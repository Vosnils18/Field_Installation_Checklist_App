// InstallationData.kt
package com.example.foxbmsinstallationchecklist

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class ProjectInfo(
    val name: String = "",
    val location: String = "",
    val contactPerson: String = "",
    val contactNumber: String = "",
    val email: String = ""
)

data class Measurements(
    val width: String = "",
    val height: String = "",
    val depth: String = "",
    val cabinetType: String = "",
    val cableEntryPosition: String = "",
    val numberOfDoors: String = "",
    val numberOfShelves: String = ""
)

@RequiresApi(Build.VERSION_CODES.O)
data class PhotoData(
    val type: String,
    val uri: String,
    val timestamp: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
)

@RequiresApi(Build.VERSION_CODES.O)
data class InstallationData(
    val projectInfo: ProjectInfo = ProjectInfo(),
    val measurements: Measurements = Measurements(),
    val photos: Map<String, String> = emptyMap(), // Key: photo type, Value: photo URI
    val timestamp: String = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
    val deviceId: String = "", // To identify which device submitted the data
    val appVersion: String = "1.0" // To track app version
) {
    // Helper function to convert to database-friendly format
    fun toDatabaseFormat(): Map<String, Any> {
        return mapOf(
            "project_name" to projectInfo.name,
            "location" to projectInfo.location,
            "contact_person" to projectInfo.contactPerson,
            "contact_number" to projectInfo.contactNumber,
            "email" to projectInfo.email,
            "width" to measurements.width,
            "height" to measurements.height,
            "depth" to measurements.depth,
            "cabinet_type" to measurements.cabinetType,
            "cable_entry_position" to measurements.cableEntryPosition,
            "number_of_doors" to measurements.numberOfDoors,
            "number_of_shelves" to measurements.numberOfShelves,
            "timestamp" to timestamp,
            "device_id" to deviceId,
            "app_version" to appVersion,
            "photos" to photos.entries.joinToString("|") { "${it.key}:${it.value}" }
        )
    }

    // Helper function to convert photos to PhotoData list
    fun getPhotoDataList(): List<PhotoData> {
        return photos.map { PhotoData(it.key, it.value) }
    }
}
