package screen.login

import Event
import Resource
import entity.User

data class UIState(
    val hasLogin: Boolean,
    val loginEvent: Event<Resource<User>>,
    val registerEvent: Event<Resource<User>>,
)
