package com.example.transport_app.presentation.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import com.example.transport_app.presentation.viewmodel.DeliveryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryConfirmationScreen(
    routeId: Int,
    onConfirmDelivery: () -> Unit,
    onRejectDelivery: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: DeliveryViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

) {
    // Puedes usar Scaffold si deseas una barra superior con flecha atrás
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Confirmar Entrega") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¿Se entrega el paquete?",
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Botón "Entregado"
            Button(
                onClick  = {
                viewModel.updateRouteStatus(routeId, "Entregado")
                onConfirmDelivery()
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE5EAFE))
            ) {
                Text(
                    text = "Entregado",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "No entregado"
            Button(
                onClick = {
                    viewModel.updateRouteStatus(routeId, "No Entregado")
                    onRejectDelivery()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEAE6))
            ) {
                Text(
                    text = "No entregado",
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
        }
    }
}
