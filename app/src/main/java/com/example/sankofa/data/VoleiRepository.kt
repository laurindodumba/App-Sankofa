package com.example.sankofa.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class VoleiRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val col = db.collection("volei_games")

    fun observeGames(): Flow<List<VoleiGame>> = callbackFlow {
        val listener = col
            .orderBy("dateTimeMillis", Query.Direction.ASCENDING)
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    trySend(emptyList())
                    return@addSnapshotListener
                }

                val list = snap?.documents?.map { doc ->
                    VoleiGame(
                        id = doc.id,
                        dateTimeMillis = doc.getLong("dateTimeMillis") ?: 0L,
                        location = doc.getString("location") ?: "",
                        createdAt = doc.getLong("createdAt") ?: 0L
                    )
                }.orEmpty()

                trySend(list)
            }

        awaitClose { listener.remove() }
    }

    fun createGame(dateTimeMillis: Long, location: String) {
        col.add(
            mapOf(
                "dateTimeMillis" to dateTimeMillis,
                "location" to location,
                "createdAt" to System.currentTimeMillis()
            )
        )
    }
}
