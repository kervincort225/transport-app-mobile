package com.example.transport_app.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.transport_app.presentation.viewmodel.DeliveryViewModel

@Composable
fun DailyPlanScreen(
    viewModel: DeliveryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
   // viewModel: DeliveryViewModel = hiltViewModel(),
    onCardClick: (Int) -> Unit
) {
    // Obtenemos la lista de rutas del ViewModel
    val routes = viewModel.routes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Plan Diario",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "A continuación se muestra tu plan de rutas diario.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Iteramos sobre la lista de rutas
        routes.forEach { route ->
            RouteCard(
                lpn = route.lpn,
                address = route.address,
                name = route.name,
                status = route.status,
                number = route.id,
                onCardClick = {
                    // Cuando se hace tap en la tarjeta,
                    // llamamos la lambda con el ID de la ruta
                    onCardClick(route.id)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}



@Composable
fun RouteCard(
    lpn: String,
    address: String,
    name: String,
    status: String,
    number: Int,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onCardClick() },
    shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Fila 1: LPN izq, N° der
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "LPN : $lpn",
                    fontSize = 14.sp,
                    color = Color(0xFF5138EE),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "N°$number",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Dirección
            Text(
                text = address,
                fontSize = 14.sp,
                color = Color.Gray
            )

            // Nombre
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = name,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Fila 2: chip a la derecha
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                StateChip(status)
            }
        }
    }
}

@Composable
fun StateChip(status: String) {
    val (bgColor, textColor) = when (status) {
        "Entregado" -> Pair(Color(0xFFE5EAFE), Color(0xFF5138EE))
        "No Entregado" -> Pair(Color(0xFFFFEAE6), Color(0xFFE84A27))
        "Libre" -> Pair(Color(0xFFE8F7EE), Color(0xFF40A54A))
        "Entregar" -> Pair(Color(0xFFDDE9FF), Color(0xFF0066FF))
        else -> Pair(Color.LightGray, Color.Black)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = status,
            fontSize = 14.sp,
            color = textColor
        )
    }
}


