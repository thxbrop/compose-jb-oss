package screen.login.use_case

import Event
import Resource
import entity.User
import retrofit.UserService

data class RegisterUseCase(
    private val userService: UserService
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        username: String
    ): Event<Resource<User>> = try {
        userService.register(email, password, username).handle({
            Event(Resource.Success(it))
        }, { code, message ->
            Event(Resource.Failure(code, message))
        })
    } catch (e: Exception) {
        Event(Resource.Failure(999, e.message ?: "Unknown error"))
    }
}
