package com.example.sankofa.ViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sankofa.data.PlayerRepository
import com.example.sankofa.data.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PlayerViewModel(
    private val repo: PlayerRepository = PlayerRepository()
) : ViewModel() {

    private val _players = MutableStateFlow<List<com.example.sankofa.Model.Player>>(emptyList())
    val players: StateFlow<List<com.example.sankofa.Model.Player>> = _players

    private var currentTeam: Team = Team.FEMININO

    fun setTeam(team: Team) {
        currentTeam = team
        viewModelScope.launch {
            repo.observePlayers(team).collectLatest { list ->
                _players.value = list
            }
        }
    }

    fun add(name: String) = repo.addPlayer(currentTeam, name)

    fun edit(player: com.example.sankofa.Model.Player, newName: String) =
        repo.updateName(currentTeam, player.id, newName)

    fun toggle(player: com.example.sankofa.Model.Player) =
        repo.toggleDone(currentTeam, player.id, !player.isDone)

    fun delete(player: com.example.sankofa.Model.Player) =
        repo.deletePlayer(currentTeam, player.id)
}
