package com.example.sankofa.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sankofa.navigation.Routes

@Composable
fun Futebol(navHostController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {

        // Top bar simples com botão voltar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF359D94))
                .padding(horizontal = 8.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "FUTEBOL",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "NOSSOS TIMES",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        TeamCard(
            title = "Time Masculino",
            players = "25 Jogadores",
            position = "2º Posição",
            backgroundColor = Color(0xFFF0F6FF),
            borderColor = Color(0xFFB3D4FF),
            onClick = { navHostController.navigate(Routes.CRUDM) }
        )

        TeamCard(
            title = "Time Feminino",
            players = "25 Jogadoras",
            position = "1º Posição",
            backgroundColor = Color(0xFFFFF0F5),
            borderColor = Color(0xFFFFB3D1),
            onClick = { navHostController.navigate(Routes.CRUDF) }
        )
    }
}

@Composable
fun TeamCard(
    title: String,
    players: String,
    position: String,
    backgroundColor: Color,
    borderColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = players, fontSize = 13.sp, color = Color.Gray)
            }

            Text(
                text = position,
                fontSize = 13.sp,
                color = Color.DarkGray,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
