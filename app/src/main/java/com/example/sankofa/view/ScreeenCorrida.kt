package com.example.sankofa.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.contentReceiver
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sankofa.R
import com.example.sankofa.navigation.Routes


@Composable
fun Screencorrida(navHostController: NavHostController) {


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
                text = "CORRIDA SANKOFEIRAS",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "PRÓXIMAS CORRIDAS",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        CorridaCard(
            title = "Corrida Sankofeira 5KM",
            players = "Atleta",
            position = "2º Posição",
            backgroundColor = Color(0xFF88B7E7),
            borderColor = Color(0xFF3F4C60),
            onClick = {
                navHostController.navigate(Routes.CRIAR_CORRIDA)
            }
        )

        CorridaCard(
            title = "Corrida Sanfokeira 15KM",
            players = "Atleta",
            position = "1º Posição",
            backgroundColor = Color(0xFF36575B),
            borderColor = Color(0xFFFFB3D1),
            onClick = {
                navHostController.navigate(Routes.CRIAR_CORRIDA)
            }
        )
    }
}

@Composable
fun CorridaCard(
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
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = players,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = position,
                fontSize = 13.sp,
                color = Color.DarkGray,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    } }