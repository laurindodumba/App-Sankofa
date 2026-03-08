package com.example.sankofa.view

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavHostController
import com.example.sankofa.ViewModel.PlayerViewModel
import com.example.sankofa.data.Team


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrudF(
    playerViewModel: PlayerViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    LaunchedEffect(Unit) {
        playerViewModel.setTeam(Team.FEMININO)
    }

    var playerName by remember { mutableStateOf("") }
    val players = playerViewModel.players.collectAsState().value



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Jogadoras") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = playerName,
                    onValueChange = { playerName = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text(text = "Nome da jogadora") },
                    shape = RoundedCornerShape(22.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Red,
                        unfocusedIndicatorColor = Color.Red,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Button(
                    onClick = {
                        val trimmed = playerName.trim()
                        if (trimmed.isNotEmpty()) {
                            playerViewModel.add(trimmed)
                            playerName = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Adicionar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = Color.Red)
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(items = players, key = { it.id }) { player ->
                    var isEditing by remember(player.id) { mutableStateOf(false) }
                    var newName by remember(player.id) { mutableStateOf(player.name) }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Checkbox(
                                checked = player.isDone,
                                onCheckedChange = { playerViewModel.toggle(player) },
                                colors = CheckboxDefaults.colors(checkedColor = Color.Red)
                            )

                            if (isEditing) {
                                OutlinedTextField(
                                    value = newName,
                                    onValueChange = { newName = it },
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .weight(1f),
                                    shape = RoundedCornerShape(22.dp),
                                    colors = TextFieldDefaults.colors(
                                        focusedIndicatorColor = Color.Red,
                                        unfocusedIndicatorColor = Color.Red,
                                        focusedContainerColor = Color.White,
                                        unfocusedContainerColor = Color.White
                                    )
                                )

                                Button(
                                    onClick = {
                                        val trimmed = newName.trim()
                                        if (trimmed.isNotEmpty()) {
                                            playerViewModel.edit(player, trimmed)
                                        }
                                        isEditing = false
                                    },
                                    modifier = Modifier.padding(start = 8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                                ) {
                                    Text(text = "Salvar")
                                }
                            } else {
                                Text(
                                    text = player.name,
                                    modifier = Modifier.padding(start = 8.dp),
                                    style = if (player.isDone)
                                        LocalTextStyle.current.copy(textDecoration = TextDecoration.LineThrough)
                                    else LocalTextStyle.current
                                )
                            }
                        }

                        Row {
                            IconButton(onClick = {
                                if (!isEditing) newName = player.name
                                isEditing = !isEditing
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Editar",
                                    tint = Color.Red
                                )
                            }

                            IconButton(onClick = { playerViewModel.delete(player) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Deletar"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
