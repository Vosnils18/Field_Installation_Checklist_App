// ui/screens/DBSettingsScreen.kt
package com.example.foxbmsinstallationchecklist.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.foxbmsinstallationchecklist.ui.theme.FoxButton
import com.example.foxbmsinstallationchecklist.ui.theme.FoxTextField
import com.example.foxbmsinstallationchecklist.utils.SecureCredentialsManager

@Composable
fun DBSettingsScreen(
    credentialsManager: SecureCredentialsManager,
    onSave: () -> Unit
) {
    var host by remember { mutableStateOf(credentialsManager.getHost()) }
    var port by remember { mutableStateOf(credentialsManager.getPort().toString()) }
    var dbName by remember { mutableStateOf(credentialsManager.getDatabaseName()) }
    var username by remember { mutableStateOf(credentialsManager.getUsername()) }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Database Settings", style = MaterialTheme.typography.titleLarge)

        // Host field
        FoxTextField(
            value = host,
            onValueChange = { host = it },
            label = "Host (NAS IP Address)",
            keyboardType = KeyboardType.Text,  // Standard text input
            modifier = Modifier.fillMaxWidth()
        )

        // Port field
        FoxTextField(
            value = port,
            onValueChange = { port = it },
            label = "Port",
            keyboardType = KeyboardType.Number,  // Number input
            modifier = Modifier.fillMaxWidth()
        )

        // Database name field
        FoxTextField(
            value = dbName,
            onValueChange = { dbName = it },
            label = "Database Name",
            keyboardType = KeyboardType.Text,  // Standard text input
            modifier = Modifier.fillMaxWidth()
        )

        // Username field
        FoxTextField(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            keyboardType = KeyboardType.Text,  // Standard text input
            modifier = Modifier.fillMaxWidth()
        )

        // Password field
        FoxTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            visualTransformation = PasswordVisualTransformation(),
            keyboardType = KeyboardType.Password,  // Password input
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        FoxButton(
            onClick = {
                credentialsManager.saveCredentials(
                    host = host,
                    port = port.toIntOrNull() ?: 3307,
                    dbName = dbName,
                    username = username,
                    password = password
                )
                onSave()
            },
            text = "Save Credentials",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
