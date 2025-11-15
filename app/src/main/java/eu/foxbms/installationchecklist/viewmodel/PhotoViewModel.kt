package eu.foxbms.installationchecklist.viewmodel

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
    fun getPhotosByEntity(entityId: Int, entityType: String) =
        photoRepository.getPhotosByEntity(entityId, entityType)

    fun insertPhoto(photo: PhotoEntity) = viewModelScope.launch {
        photoRepository.insertPhoto(photo)
    }

    fun deletePhoto(id: Int) = viewModelScope.launch {
        photoRepository.deletePhoto(id)
    }
}
