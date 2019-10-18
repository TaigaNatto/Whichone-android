package natto.com.whichone_android.ui.main

import androidx.lifecycle.MutableLiveData
import natto.com.whichone_android.domain.User

class UserLiveData : MutableLiveData<User>() {
    override fun onActive() {
        super.onActive()
    }
}