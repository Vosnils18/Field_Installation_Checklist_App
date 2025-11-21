package eu.foxbms.installationchecklist.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import eu.foxbms.installationchecklist.data.local.PhotoEntity
import eu.foxbms.installationchecklist.data.repository.PhotoRepository
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {

    var tempPhotoUri by mutableStateOf<Uri?>(null)

    fun getPhotosByEntity(entityId: Int, entityType: String) =
        photoRepository.getPhotosByEntity(entityId, entityType)

    fun insertPhoto(photo: PhotoEntity) = viewModelScope.launch {
        photoRepository.insertPhoto(photo)
    }

    fun deletePhoto(id: Int) = viewModelScope.launch {
        photoRepository.deletePhoto(id)
    }
}