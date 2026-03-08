package com.example.sankofa.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
//mport coil.compose.rememberAsyncImagePainter


@Composable
fun Corres(navController: NavController) {

    var nome by remember { mutableStateOf("") }
    var profissao by remember { mutableStateOf("") }
    var empresa by remember { mutableStateOf("") }
    var experiencia by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var gerarCard by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // HEADER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF359D94))
                .padding(horizontal = 8.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White
                )
            }
        }

        // CONTEÚDO
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Inserir informações profissionais",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // FOTO
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    Text("Foto")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome completo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = profissao,
                onValueChange = { profissao = it },
                label = { Text("Profissão") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = empresa,
                onValueChange = { empresa = it },
                label = { Text("Empresa") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = experiencia,
                onValueChange = { experiencia = it },
                label = { Text("Anos de experiência") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = { Text("Descrição profissional") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { gerarCard = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Gerar Card")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (gerarCard) {
                ProfissionalCard(
                    imageUri = imageUri,
                    nome = nome,
                    profissao = profissao,
                    empresa = empresa,
                    experiencia = experiencia,
                    descricao = descricao
                )
            }
        }
    }
}


@Composable
fun ProfissionalCard(
    imageUri: Uri?,
    nome: String,
    profissao: String,
    empresa: String,
    experiencia: String,
    descricao: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (imageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(nome, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(profissao, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Empresa: $empresa")
            Text("Experiência: $experiencia anos")

            Spacer(modifier = Modifier.height(8.dp))

            Text(descricao)
        }
    }
}
