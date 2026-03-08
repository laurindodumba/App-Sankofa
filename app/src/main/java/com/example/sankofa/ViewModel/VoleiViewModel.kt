package com.example.sankofa.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sankofa.data.VoleiRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class VoleiViewModel(
    private val repo: VoleiRepository = VoleiRepository()
) : ViewModel() {

    val games = repo.observeGames()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun criarJogo(dateTimeMillis: Long, location: String) {
        repo.createGame(dateTimeMillis, location)
    }
}
