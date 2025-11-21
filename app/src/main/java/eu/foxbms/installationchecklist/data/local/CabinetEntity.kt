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
    val projectId: Long, // Links to Project
    val voltage: String,
    val frequency: Double,
    val current: Double,
    val siteConditions: String,
    val material: String,
    val colour: String,
    val ingressProtection: String,
    val mounting: String,
    val cabinetAccessories: String,
    val cableEntryPosition: String, // Onder/Boven
    val cableEntryType: String,
    val location: String,
    val finalComments: String,

    // New fields from APP_BENODIGDHEDEN.txt
    val width: Double, // breedte
    val height: Double, // hoogte
    val depth: Double, // diepte
    val numberOfDoors: Int, // aantal deuren
    val doorSwingDirection: String, // linksdraaiend, rechtdraaiend / positie slot
    val intakeGrillePosition: String, // positie aanzuigrooster
    val exhaustGrillePosition: String, // positie afblaasrooster
    val freeSpaceRight: Double, // vrije ruimte rechts
    val freeSpaceLeft: Double, // vrije ruimte links
    val freeSpaceTop: Double, // vrije ruimte boven
    val freeSpaceBottom: Double, // vrije ruimte onder
    val photosTakenOutside: Boolean = false, // maak fotos buitenzijde
    val photosTakenInside: Boolean = false, // maak fotos binnenzijde
    val photoOfCabinetSent: Boolean = false // stuurt foto van kast
)
