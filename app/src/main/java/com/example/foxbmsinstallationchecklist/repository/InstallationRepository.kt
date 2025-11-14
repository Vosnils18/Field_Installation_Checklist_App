// repository/InstallationRepository.kt
package com.example.foxbmsinstallationchecklist.repository

import com.example.foxbmsinstallationchecklist.InstallationData
import com.example.foxbmsinstallationchecklist.database.MariaDBHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InstallationRepository(
    private val dbHelper: MariaDBHelper
) {
    suspend fun submitInstallationData(data: InstallationData): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                // Connect to the database
                val connected = dbHelper.connect()
                if (!connected) {
                    println("Database connection failed")
                    return@withContext false
                }

                // Insert the installation data
                val success = dbHelper.insertInstallationData(data)
                dbHelper.disconnect()

                if (success) {
                    println("Data submitted successfully")
                } else {
                    println("Data submission failed")
                }

                success
            } catch (e: Exception) {
                println("Error submitting data: ${e.message}")
                e.printStackTrace()
                return@withContext false
            }
        }
    }
}
