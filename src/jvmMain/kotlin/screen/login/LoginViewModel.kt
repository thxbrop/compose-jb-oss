package screen.login

import Event
import Resource
import ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import screen.login.use_case.LoginUseCase
import screen.login.use_case.RegisterUseCase

class LoginViewModel private constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            LoginViewModel(
                loginUseCase = LoginUseCase(
                    userService = provideService()
                ),
                registerUseCase = RegisterUseCase(
                    userService = provideService()
                )
            )
        }
    }

    private var _uiState = MutableStateFlow(
        UIState(
            hasLogin = false, loginEvent = Event.empty(), registerEvent = Event.empty()
        )
    )
    val uiState: StateFlow<UIState> get() = _uiState

    fun login(
        scope: CoroutineScope,
        email: String,
        password: String
    ) {
        scope.launch {
            _uiState.emit(_uiState.value.copy(loginEvent = Event(Resource.Loading)))
            val event = loginUseCase(email, password)
            _uiState.emit(_uiState.value.copy(loginEvent = event))
        }
    }

    fun register(
        scope: CoroutineScope,
        email: String,
        password: String,
        username: String
    ) {
        scope.launch {
            _uiState.emit(_uiState.value.copy(registerEvent = Event(Resource.Loading)))
            val event = registerUseCase(email, password, username)
            _uiState.emit(_uiState.value.copy(registerEvent = event))
        }
    }

}