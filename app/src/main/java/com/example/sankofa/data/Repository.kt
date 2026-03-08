package com.example.sankofa.data


import com.example.sankofa.Model.Debate
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class DebateRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val col = db.collection("debates")

    fun observeDebates(): Flow<List<Debate>> = callbackFlow {
        val listener = col
            .orderBy("dateTimeMillis", Query.Direction.ASCENDING)
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    trySend(emptyList())
                    return@addSnapshotListener
                }

                val list = snap?.documents?.map { doc ->
                    Debate(
                        id = doc.id,
                        title = doc.getString("title") ?: "Conversa Sankofeira",
                        dateTimeMillis = doc.getLong("dateTimeMillis") ?: 0L,
                        location = doc.getString("location") ?: "",
                        createdAt = doc.getLong("createdAt") ?: 0L
                    )
                }.orEmpty()

                trySend(list)
            }

        awaitClose { listener.remove() }
    }

    fun createDebate(title: String, dateTimeMillis: Long, location: String) {
        col.add(
            mapOf(
                "title" to title,
                "dateTimeMillis" to dateTimeMillis,
                "location" to location,
                "createdAt" to System.currentTimeMillis()
            )
        )
    }
}
