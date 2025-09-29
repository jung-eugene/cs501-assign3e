package com.example.loginform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginform.ui.theme.LoginFormTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginFormTheme {
                MyLoginApp()
            }
        }
    }
}

@Composable
fun MyLoginApp() {
    // Snackbar host state to show success message
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { inner ->
        LoginForm(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
                .padding(24.dp), // padding around the form
            snackbarHostState = snackbarHostState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(modifier: Modifier = Modifier, snackbarHostState: SnackbarHostState) {
    // Form state variables
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }

    // Error messages for validation
    var userError by remember { mutableStateOf<String?>(null) }
    var passError by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Login", style = MaterialTheme.typography.headlineSmall)

        // Username field
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                if (userError != null) userError = null
            },
            label = { Text("Username") },
            singleLine = true,
            isError = userError != null,
            supportingText = { if (userError != null) Text(userError!!) },
            modifier = Modifier.fillMaxWidth()
        )

        // Password field with show/hide toggle
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                if (passError != null) passError = null
            },
            label = { Text("Password") },
            singleLine = true,
            isError = passError != null,
            supportingText = { if (passError != null) Text(passError!!) },
            visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                TextButton(onClick = { showPass = !showPass }) {
                    Text(if (showPass) "Hide" else "Show")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Submit button
        Button(
            onClick = {
                var ok = true
                if (username.isBlank()) { userError = "Username is required"; ok = false }
                if (password.isBlank()) { passError = "Password is required"; ok = false }
                if (ok) {
                    // Show snackbar if form is valid
                    scope.launch { snackbarHostState.showSnackbar("Login submitted") }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogin() {
    LoginFormTheme { MyLoginApp() }
}
