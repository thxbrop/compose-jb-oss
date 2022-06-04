sealed class Resource<out T> {
    data class Success<T>(
        val data: T
    ) : Resource<T>()

    data class Failure(
        val code: Int,
        val message: String
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}