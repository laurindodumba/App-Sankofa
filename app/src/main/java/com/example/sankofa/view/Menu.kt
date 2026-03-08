package com.example.sankofa.view
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sankofa.R
import com.example.sankofa.navigation.Routes
import androidx.compose.ui.graphics.Brush





@Composable
fun Menu(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1.2f))

        val borderWidth = 0.dp
        Image(
            painter = painterResource(R.drawable.sankofa),
            contentDescription = null,
           // modifier = Modifier.size(300.dp),

            contentScale = ContentScale.Fit,
            modifier = Modifier.size(280.dp)
                .size(200.dp)
                .border(
                    BorderStroke(borderWidth, Color.White),
            CircleShape
        )
            .padding(borderWidth)
            .clip(CircleShape)
                .clip(RoundedCornerShape(18.dp))
        )
        Text(text = "ESCOLHA O SEU ESPORTE !")

        Spacer(modifier = Modifier.height(24.dp))

        // CARROSSEL logo abaixo da imagem
        Carrossel{
            route ->
            navHostController.navigate(route)
        }


        // Espaço inferior para equilibrar
        Spacer(modifier = Modifier.weight(0.8f))


        SankofaFooter()
    }
}

@Composable
fun Carrossel(onItemClick: (String) -> Unit) {

    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        val contentDescription: String,
        val route: String,
        val descricao: String
    )

    val items = remember {
        listOf(
            CarouselItem(0, R.drawable.masculino, "Futebol", Routes.FUTEBOL, descricao = "FUTEBOL"),
            CarouselItem(1, R.drawable.corres, "Corre", Routes.CORRES, descricao = "CORRE SANKOFEIRO"),
            CarouselItem(2, R.drawable.corrida, "Corrida", Routes.SCREEENCORRIDA, descricao = "CORRIDA"),
            CarouselItem(3, R.drawable.volei, "Vôlei", Routes.VOLEI, descricao = "VÔLEI"),
            CarouselItem(4, R.drawable.debate, "Debate", Routes.SCREENDEBATE, descricao = "DEBATE"),
        )
    }

    val itemWidth = 160.dp
    val spacing = 16.dp

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val sidePadding = (maxWidth - itemWidth) / 2

        LazyRow(
            modifier = Modifier.height(210.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing),
            contentPadding = PaddingValues(horizontal = sidePadding)
        ) {
            items(items) { item ->

                Column(
                    modifier = Modifier.width(itemWidth),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){


                Image(
                    painter = painterResource(item.imageResId),
                    contentDescription = item.contentDescription,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(width = itemWidth, height = 180.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .clickable { onItemClick(item.route) }
                )

                Spacer(modifier = Modifier.height(1.dp))


                    Text(
                        text = item.descricao,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )

                }
            }
        }
    }
}

@Composable
fun SankofaFooter() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp) // altura do rodapé
    ) {

        // Imagem de fundo
        Image(
            painter = painterResource(R.drawable.rodape), // sua imagem
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // uavizar a cor
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.85f),
                            Color.Black.copy(alpha = 0.45f),
                            Color.Black.copy(alpha = 0.85f)
                        )
                    )
                )
        )
    }
}

