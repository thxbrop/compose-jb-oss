package retrofit

import Result
import entity.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("/user/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Result<User>

    @GET("/user/register")
    suspend fun register(
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("username") username: String?
    ): Result<User>
}