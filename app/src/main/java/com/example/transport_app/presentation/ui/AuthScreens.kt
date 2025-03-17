package com.example.transport_app.presentation.ui
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.Color

@Composable
fun AuthenticationScreen(
    onRegisterSuccess: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Entrar", "Registrate")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "transport app",
            modifier = Modifier.padding(top = 32.dp, bottom = 24.dp),
            fontSize = 24.sp,
            color = Color.Black
        )
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            indicator = {},
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 28.sp,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTabIndex == index) Color.Black else Color.Gray
                        )
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(3.dp)
                    .padding(end = if (selectedTabIndex == 0) 0.dp else 8.dp),
                color = if (selectedTabIndex == 0) Color(0xFF5138EE) else Color.Transparent
            )
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .height(3.dp)
                    .padding(start = if (selectedTabIndex == 1) 0.dp else 8.dp),
                color = if (selectedTabIndex == 1) Color(0xFF5138EE) else Color.Transparent
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        when (selectedTabIndex) {
            0 -> LoginContent(
                onLoginClicked = { onLoginSuccess() }
            )
            1 -> RegisterContent(
                onCreateAccountClicked = {
                    // Cuando se presiona "Crear Cuenta", se llama el callback
                    onRegisterSuccess()
                }
            )
        }
    }
}

@Composable
fun LoginContent(
    onLoginClicked: () -> Unit
) {
    Text(
        text = "Inicia sesión con tu usuario y contraseña o ingresa utilizando tu cuenta de google",
        fontSize = 16.sp,
        color = Color.Gray,
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Email field
    var email by remember { mutableStateOf("") }
    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color(0xFF5138EE)
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Password field
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            TextButton(
                onClick = { passwordVisible = !passwordVisible },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(
                    text = if (passwordVisible) "Ocultar" else "Mostrar",
                    fontSize = 11.sp,
                    color = Color(0xFF5138EE)
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color(0xFF5138EE)
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Forgot password
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            onClick = { /* TODO: Handle forgot password */ }
        ) {
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = Color.Black
            )
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Login button
    Button(
        onClick = {  onLoginClicked()},
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF5138EE)
        )
    ) {
        Text(
            text = "Entrar",
            fontSize = 18.sp
        )
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Or continue with
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "O inicia sesión con",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Google login button
    Button(
        onClick = { /* TODO: Handle Google login */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Note: You would need to add the Google icon to your project resources
            // Icon(painter = painterResource(id = R.drawable.ic_google), contentDescription = "Google")
            Text(
                text = "G",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Continue with Google",
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun RegisterContent(
    onCreateAccountClicked: () -> Unit
) {
    Text(
        text = "Registrate con tu usuario y contraseña o ingresa utilizando tu cuenta de google",
        fontSize = 16.sp,
        color = Color.Gray,
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Email field
    var email by remember { mutableStateOf("") }
    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color(0xFF5138EE)
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Password field
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            TextButton(
                onClick = { passwordVisible = !passwordVisible },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(
                    text = if (passwordVisible) "Ocultar" else "Mostrar",
                    fontSize = 11.sp,
                    color = Color(0xFF5138EE)
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color(0xFF5138EE)
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Confirm Password field
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it },
        label = { Text("Confirmar Contraseña") },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            TextButton(
                onClick = { confirmPasswordVisible = !confirmPasswordVisible },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text(
                    text = if (confirmPasswordVisible) "Ocultar" else "Mostrar",
                    fontSize = 11.sp,
                    color = Color(0xFF5138EE)
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.LightGray,
            focusedBorderColor = Color(0xFF5138EE)
        )
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Botón "Crear Cuenta"
    Button(
        onClick = {
            // Aquí puedes hacer validaciones antes de navegar
            onCreateAccountClicked()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5138EE))
    ) {
        Text(text = "Crear Cuenta", fontSize = 18.sp)
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Texto "O inicia sesión con"
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "O inicia sesión con",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Botón "Continue with Google"
    Button(
        onClick = { /* TODO: Handle Google login */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Aquí podrías poner el ícono de Google si lo tienes en tus recursos
            Text(
                text = "G",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Continue with Google",
                fontSize = 16.sp
            )
        }
    }
}

