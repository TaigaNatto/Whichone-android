package natto.com.whichone_android.api.firebase.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import natto.com.whichone_android.api.firebase.FirebaseApi
import natto.com.whichone_android.common.ConnectListener
import natto.com.whichone_android.domain.User

class FirebaseApiImpl(private val connectListener: ConnectListener) : FirebaseApi {

    private val dbInstance: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun addData(
        user: User
    ) {
        dbInstance.collection("users")
            .add(user)
            .addOnSuccessListener {
                connectListener.onSuccess().invoke()
            }
            .addOnFailureListener {
                connectListener.onFailed().invoke(it)
            }
    }

    override suspend fun realtimeListener(
        collectionName: String,
        onSnapshot: ((QuerySnapshot) -> Unit)
    ) {
        val docRef = dbInstance.collection(collectionName)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                // failed
                return@addSnapshotListener
            }

            if (snapshot != null && !(snapshot.isEmpty)) {
                onSnapshot.invoke(snapshot)
            } else {
                // null data
            }
        }
    }
}