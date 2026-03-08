package com.example.sankofa.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sankofa.Model.Debate
import com.example.sankofa.data.DebateRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class DebateViewModel(
    private val repo: DebateRepository = DebateRepository()
) : ViewModel() {

    val debates = repo.observeDebates()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList<Debate>()
        )

    fun CriarDebate(title: String, dateTimeMillis: Long, location: String) {
        repo.createDebate(title, dateTimeMillis, location)
    }
}
