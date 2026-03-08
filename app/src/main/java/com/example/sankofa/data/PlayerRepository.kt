package com.example.sankofa.data

import com.example.sankofa.Model.Player
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class PlayerRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {

    private fun col(team: Team) = when (team) {
        Team.FEMININO -> db.collection("players_feminino")
        Team.MASCULINO -> db.collection("players_masculino")
    }

    fun observePlayers(team: Team): Flow<List<Player>> = callbackFlow {
        val listener = col(team)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snap, err ->
                if (err != null) {
                    close(err)
                    return@addSnapshotListener
                }
                val list = snap?.documents?.map { doc ->
                    Player(
                        id = doc.id,
                        name = doc.getString("name") ?: "",
                        isDone = doc.getBoolean("isDone") ?: false,
                        createdAt = doc.getLong("createdAt") ?: 0L
                    )
                }.orEmpty()
                trySend(list)
            }

        awaitClose { listener.remove() }
    }

    fun addPlayer(team: Team, name: String) {
        col(team).add(
            mapOf(
                "name" to name,
                "isDone" to false,
                "createdAt" to System.currentTimeMillis()
            )
        )
    }

    fun updateName(team: Team, id: String, newName: String) {
        col(team).document(id).update("name", newName)
    }

    fun toggleDone(team: Team, id: String, newValue: Boolean) {
        col(team).document(id).update("isDone", newValue)
    }

    fun deletePlayer(team: Team, id: String) {
        col(team).document(id).delete()
    }
}
