package screen.login

import Resource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import res.R

@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState
) {
    val scope = rememberCoroutineScope()
    val viewModel by remember { mutableStateOf(LoginViewModel.instance) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isRequesting by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(360.dp)
                .fillMaxHeight()
                .padding(
                    vertical = 36.dp
                )
        ) {
            Image(
                painter = R.drawable.icon(),
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Text(
                text = if (isRequesting) R.string.screen_login_title_requesting else R.string.screen_login_title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(
                    top = 36.dp
                )
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 24.dp
                    ),
                label = {
                    Text(
                        text = R.string.screen_login_label_email,
                        fontSize = 16.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                enabled = !isRequesting
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 6.dp
                    ),
                label = {
                    Text(
                        text = R.string.screen_login_label_password,
                        fontSize = 16.sp
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                enabled = !isRequesting
            )
            Button(
                onClick = {
                    viewModel.login(scope, email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 18.dp
                    ),
                enabled = !isRequesting
            ) {
                Text(
                    text = R.string.screen_login_button_login
                )
            }
            TextButton(
                onClick = {
                    viewModel.register(scope, email, password, "1")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 4.dp
                    ),
                enabled = !isRequesting
            ) {
                Text(
                    text = R.string.screen_login_button_register
                )
            }
        }
    }
    scope.launch {
        viewModel.uiState.collectLatest { state ->
            state.loginEvent.takeIfNotHandled { event ->
                scope.launch {
                    when (event) {
                        is Resource.Success -> {
                            isRequesting = false
                            scaffoldState.snackbarHostState.showSnackbar(event.data.toString())
                        }

                        is Resource.Failure -> {
                            isRequesting = false
                            scaffoldState.snackbarHostState.showSnackbar(event.message)
                        }

                        Resource.Loading -> {
                            isRequesting = true
                        }
                    }
                }
            }

            state.registerEvent.takeIfNotHandled { event ->
                scope.launch {
                    when (event) {
                        is Resource.Success -> {
                            isRequesting = false
                            scaffoldState.snackbarHostState.showSnackbar("注册成功")
                        }

                        is Resource.Failure -> {
                            isRequesting = false
                            scaffoldState.snackbarHostState.showSnackbar(event.message)
                        }

                        Resource.Loading -> {
                            isRequesting = true
                        }
                    }
                }
            }
        }
    }
}
