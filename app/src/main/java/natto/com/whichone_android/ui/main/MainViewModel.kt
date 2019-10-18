package natto.com.whichone_android.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import natto.com.whichone_android.domain.User
import natto.com.whichone_android.domain.repository.FireStoreRepository

class MainViewModel(
    application: Application,
    private val fireStoreRepository: FireStoreRepository
) : AndroidViewModel(application) {
    val userLiveData = MutableLiveData<User>()

    init {
        viewModelScope.launch {
            fireStoreRepository.checkUserSnapshot {
                val changeData = it.documentChanges[0].document.data
                val user = User(changeData["id"] as Long, changeData["name"] as String)
                userLiveData.value = user
            }
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            fireStoreRepository.addUser(user)
        }
    }
}