// ChecklistViewModelFactory.kt
package com.example.foxbmsinstallationchecklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChecklistViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChecklistViewModel::class.java)) {
            return ChecklistViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
