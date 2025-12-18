package eu.foxbms.installationchecklist.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProjectDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("mainContact") val mainContact: String,
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("updatedAt") val updatedAt: Long
)

data class CabinetDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("projectId") val projectId: Long,
    @SerializedName("voltage") val voltage: String,
    @SerializedName("frequency") val frequency: Double,
    @SerializedName("current") val current: Double,
    @SerializedName("siteConditions") val siteConditions: String,
    @SerializedName("material") val material: String,
    @SerializedName("colour") val colour: String,
    @SerializedName("ingressProtection") val ingressProtection: String,
    @SerializedName("mounting") val mounting: String,
    @SerializedName("cabinetAccessories") val cabinetAccessories: String,
    @SerializedName("cableEntryPosition") val cableEntryPosition: String,
    @SerializedName("cableEntryType") val cableEntryType: String,
    @SerializedName("location") val location: String,
    @SerializedName("finalComments") val finalComments: String,
    @SerializedName("width") val width: Double,
    @SerializedName("height") val height: Double,
    @SerializedName("depth") val depth: Double,
    @SerializedName("numberOfDoors") val numberOfDoors: Int,
    @SerializedName("doorSwingDirection") val doorSwingDirection: String,
    @SerializedName("intakeGrillePosition") val intakeGrillePosition: String,
    @SerializedName("exhaustGrillePosition") val exhaustGrillePosition: String,
    @SerializedName("freeSpaceRight") val freeSpaceRight: Double,
    @SerializedName("freeSpaceLeft") val freeSpaceLeft: Double,
    @SerializedName("freeSpaceTop") val freeSpaceTop: Double,
    @SerializedName("freeSpaceBottom") val freeSpaceBottom: Double,
)

data class PhotoDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("entityId") val entityId: Int,
    @SerializedName("entityType") val entityType: String,
    @SerializedName("uri") val uri: String? = null,
    @SerializedName("imageBase64") val imageBase64: String? = null,
    @SerializedName("createdAt") val createdAt: Long
)