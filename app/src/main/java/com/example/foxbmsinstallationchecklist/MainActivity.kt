// MainActivity.kt
package com.example.foxbmsinstallationchecklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foxbmsinstallationchecklist.navigation.NavGraph
import com.example.foxbmsinstallationchecklist.ui.theme.FoxBMSChecklistTheme
import com.example.foxbmsinstallationchecklist.viewmodel.ChecklistViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoxBMSChecklistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: ChecklistViewModel = viewModel()
                    NavGraph(viewModel = viewModel)
                }
            }
        }
    }
}
