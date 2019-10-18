package natto.com.whichone_android.domain.repository.impl

import com.google.firebase.firestore.QuerySnapshot
import natto.com.whichone_android.api.firebase.FirebaseApi
import natto.com.whichone_android.domain.User
import natto.com.whichone_android.domain.repository.FireStoreRepository

class FireStoreRepositoryImpl(firebaseApi: FirebaseApi) : FireStoreRepository {
    private val api: FirebaseApi = firebaseApi

    override suspend fun addUser(
        user: User
    ) {
        api.addData(
            user = user
        )
    }

    override suspend fun checkUserSnapshot(
        onSnapshot: ((QuerySnapshot) -> Unit)
    ) {
        api.realtimeListener("users", onSnapshot)
    }
}