package com.example.sankofa.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sankofa.data.CorridaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CorridaViewModel(
    private val repo: CorridaRepository = CorridaRepository()
) : ViewModel() {

    val corridas = repo.observeCorridas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun criarCorrida(title: String, location: String, dateTimeMillis: Long) {
        repo.createCorrida(title, location, dateTimeMillis)
    }
}
