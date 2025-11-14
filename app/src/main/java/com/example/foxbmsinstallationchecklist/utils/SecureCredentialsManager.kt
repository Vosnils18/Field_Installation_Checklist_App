// utils/SecureCredentialsManager.kt
package com.example.foxbmsinstallationchecklist.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SecureCredentialsManager(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secure_db_credentials",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveCredentials(host: String, port: Int, dbName: String, username: String, password: String) {
        with(sharedPreferences.edit()) {
            putString("db_host", host)
            putInt("db_port", port)
            putString("db_name", dbName)
            putString("db_username", username)
            putString("db_password", password)
            apply()
        }
    }

    fun getHost(): String = sharedPreferences.getString("db_host", "") ?: ""
    fun getPort(): Int = sharedPreferences.getInt("db_port", 3306)
    fun getDatabaseName(): String = sharedPreferences.getString("db_name", "") ?: ""
    fun getUsername(): String = sharedPreferences.getString("db_username", "") ?: ""
    fun getPassword(): String = sharedPreferences.getString("db_password", "") ?: ""

    fun hasCredentials(): Boolean {
        return getHost().isNotEmpty() &&
                getDatabaseName().isNotEmpty() &&
                getUsername().isNotEmpty() &&
                getPassword().isNotEmpty()
    }

    fun clearCredentials() {
        sharedPreferences.edit().clear().apply()
    }
}
