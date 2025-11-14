package com.example.foxbmsinstallationchecklist

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

data class InstallationData(
    val projectInfo: ProjectInfo = ProjectInfo(),
    val measurements: Measurements = Measurements(),
    val photos: Map<String, String> = emptyMap() // Key: photo type (e.g., "outside"), Value: photo URI
)
