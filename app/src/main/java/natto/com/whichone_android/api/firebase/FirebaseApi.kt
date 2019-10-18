package natto.com.whichone_android.api.firebase

import com.google.firebase.firestore.QuerySnapshot
import natto.com.whichone_android.domain.User

interface FirebaseApi {
    suspend fun addData(
        user: User
    )

    suspend fun realtimeListener(
        collectionName: String,
        onSnapshot: ((QuerySnapshot) -> Unit)
    )
}