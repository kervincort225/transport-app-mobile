package com.example.transport_app.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class RouteItem(
    val id: Int,
    val lpn: String,
    val address: String,
    val name: String,
    var status: String
)
@HiltViewModel
class DeliveryViewModel @Inject constructor() : ViewModel() {
    private val _routes = mutableStateListOf<RouteItem>()
    val routes: List<RouteItem> = _routes

    init {
        // Datos de ejemplo
        _routes.addAll(
            listOf(
                RouteItem(
                    id = 1,
                    lpn = "429242424",
                    address = "INGLATERRA 59, LA FLORIDA\nN°: 2214",
                    name = "Ignacio Jeria",
                    status = "Entregado"
                ),
                RouteItem(
                    id = 2,
                    lpn = "429242424",
                    address = "Santorini poniente, 1619",
                    name = "Sin Nombre",
                    status = "No Entregado"
                ),
                RouteItem(
                    id = 3,
                    lpn = "429242424",
                    address = "LOS SAUCES 28, LA FLORIDA\nN°: 28",
                    name = "Felipe Pozo",
                    status = "Entregar"
                ),
                RouteItem(
                    id = 4,
                    lpn = "429242424",
                    address = "Punto no optimizado 1234\nN°: 28",
                    name = "Ismael Vergara",
                    status = "Libre"
                )
            )
        )
    }

    // Ejemplo de actualización
    fun updateRouteStatus(routeId: Int, newStatus: String) {
        val index = _routes.indexOfFirst { it.id == routeId }
        if (index != -1) {
            _routes[index] = _routes[index].copy(status = newStatus)
        }
    }
}
