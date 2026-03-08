package com.example.sankofa.data

import com.example.sankofa.Model.Corrida
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CorridaRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val col = db.collection("corridas")

    fun observeCorridas(): Flow<List<Corrida>> = callbackFlow {
        val listener = col
            .orderBy("dateTimeMillis", Query.Direction.ASCENDING)
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    trySend(emptyList())
                    return@addSnapshotListener
                }

                val list = snap?.documents?.map { doc ->
                    Corrida(
                        id = doc.id,
                        title = doc.getString("title") ?: "",
                        location = doc.getString("location") ?: "",
                        dateTimeMillis = doc.getLong("dateTimeMillis") ?: 0L,
                        createdAt = doc.getLong("createdAt") ?: 0L
                    )
                }.orEmpty()

                trySend(list)
            }

        awaitClose { listener.remove() }
    }

    fun createCorrida(title: String, location: String, dateTimeMillis: Long) {
        col.add(
            mapOf(
                "title" to title,
                "location" to location,
                "dateTimeMillis" to dateTimeMillis,
                "createdAt" to System.currentTimeMillis()
            )
        )
    }
}
