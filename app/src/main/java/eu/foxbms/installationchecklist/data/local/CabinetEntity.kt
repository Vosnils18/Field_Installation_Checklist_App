package eu.foxbms.installationchecklist.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cabinets",
    foreignKeys = [
        ForeignKey(
            entity = Project::class,
            parentColumns = ["id"],
            childColumns = ["projectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Cabinet(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val projectId: Long,
    val voltage: String,
    val frequency: Double,
    val current: Double,
    val siteConditions: String,
    val material: String,
    val colour: String,
    val ingressProtection: String,
    val mounting: String,
    val cabinetAccessories: String,
    val cableEntryPosition: String,
    val cableEntryType: String,
    val location: String,
    val finalComments: String,
    val width: Double,
    val height: Double,
    val depth: Double,
    val numberOfDoors: Int,
    val doorSwingDirection: String,
    val intakeGrillePosition: String,
    val exhaustGrillePosition: String,
    val freeSpaceRight: Double,
    val freeSpaceLeft: Double,
    val freeSpaceTop: Double,
    val freeSpaceBottom: Double,
//    val photosTakenOutside: Boolean = false,
//    val photosTakenInside: Boolean = false,
//    val photoOfCabinetSent: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
