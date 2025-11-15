package eu.foxbms.installationchecklist.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "field_devices",
    foreignKeys = [
        ForeignKey(
            entity = CabinetEntity::class,
            parentColumns = ["id"],
            childColumns = ["cabinetId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FieldDeviceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cabinetId: Int,
    val name: String,
    val type: String,
    val serialNumber: String,
    val createdAt: Long,
    val updatedAt: Long
)
