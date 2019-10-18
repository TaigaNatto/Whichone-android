package natto.com.whichone_android.domain.repository

import com.google.firebase.firestore.QuerySnapshot
import natto.com.whichone_android.domain.User

interface FireStoreRepository {
    suspend fun addUser(
        user: User
    )

    suspend fun checkUserSnapshot(
        onSnapshot: ((QuerySnapshot) -> Unit)
    )
}