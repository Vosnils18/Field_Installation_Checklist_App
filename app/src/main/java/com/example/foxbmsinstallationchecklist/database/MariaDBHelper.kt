// database/MariaDBHelper.kt
package com.example.foxbmsinstallationchecklist.database

import com.example.foxbmsinstallationchecklist.InstallationData
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.SQLException

class MariaDBHelper(
    private val host: String,
    private val port: Int,
    private val databaseName: String,
    private val username: String,
    private val password: String
) {
    private var connection: Connection? = null

    init {
        try {
            Class.forName("org.mariadb.jdbc.Driver")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    fun connect(): Boolean {
        return try {
            val url = "jdbc:mariadb://$host:$port/$databaseName"
            connection = DriverManager.getConnection(url, username, password)
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }

    fun disconnect() {
        try {
            connection?.close()
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        connection = null
    }

    fun insertInstallationData(data: InstallationData): Boolean {
        if (connection == null && !connect()) {
            return false
        }

        return try {
            val query = """
                INSERT INTO installation_checklists
                (project_name, location, contact_person, contact_number, email,
                 width, height, depth, cabinet_type, cable_entry_position,
                 number_of_doors, number_of_shelves, timestamp)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())
            """.trimIndent()

            val statement: PreparedStatement = connection?.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS) ?: return false

            statement.setString(1, data.projectInfo.name)
            statement.setString(2, data.projectInfo.location)
            statement.setString(3, data.projectInfo.contactPerson)
            statement.setString(4, data.projectInfo.contactNumber)
            statement.setString(5, data.projectInfo.email)
            statement.setString(6, data.measurements.width)
            statement.setString(7, data.measurements.height)
            statement.setString(8, data.measurements.depth)
            statement.setString(9, data.measurements.cabinetType)
            statement.setString(10, data.measurements.cableEntryPosition)
            statement.setString(11, data.measurements.numberOfDoors)
            statement.setString(12, data.measurements.numberOfShelves)

            statement.executeUpdate()

            // Get the generated ID
            val generatedKeys = statement.generatedKeys
            val installationId = if (generatedKeys.next()) generatedKeys.getInt(1) else 1

            // Insert photos with the installation ID
            if (data.photos.isNotEmpty()) {
                insertPhotos(data.photos, installationId)
            }

            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }

    private fun insertPhotos(photos: Map<String, String>, installationId: Int): Boolean {
        return try {
            val query = "INSERT INTO installation_photos (installation_id, photo_type, photo_uri) VALUES (?, ?, ?)"
            val statement: PreparedStatement = connection?.prepareStatement(query) ?: return false

            for ((type, uri) in photos) {
                statement.setInt(1, installationId)
                statement.setString(2, type)
                statement.setString(3, uri)
                statement.addBatch()
            }

            statement.executeBatch()
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }
}
