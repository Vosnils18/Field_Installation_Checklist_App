package eu.foxbms.installationchecklist.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "field_devices",
    foreignKeys = [
        ForeignKey(
            entity = Cabinet::class,
            parentColumns = ["id"],
            childColumns = ["cabinetId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FieldDevice(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cabinetId: Long, // Links to Cabinet
    val name: String,
    val type: String,
    val serialNumber: String,
    val createdAt: Long,
    val updatedAt: Long,
    val deviceData: String // Free-text field for mechanic's notes
)