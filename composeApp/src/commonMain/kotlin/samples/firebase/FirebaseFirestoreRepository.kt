package samples.firebase

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.flow

class FirebaseFirestoreRepository {
    private val firestore = Firebase.firestore

    fun getWelcomeMessage() = flow {
        firestore.collection("Meta")
            .document("WelcomeMessageKey")
            .snapshots.collect {
                val message = (it.get("message") as? String) ?: "Welcome"
                emit(message)
            }
    }
}