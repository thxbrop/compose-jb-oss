package screen.login.use_case

import Event
import Resource
import entity.User
import retrofit.UserService

data class LoginUseCase(
    private val userService: UserService
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Event<Resource<User>> = try {
        userService.login(email, password).handle({
            Event(Resource.Success(it))
        }, { code, message ->
            Event(Resource.Failure(code, message))
        })
    } catch (e: Exception) {
        Event(Resource.Failure(999, e.message ?: "Unknown error"))
    }

}
