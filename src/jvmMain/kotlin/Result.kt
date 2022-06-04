data class Result<T>(
    val data: T? = null,
    val code: Int = 200,
    val message: String? = null
) {
    inline fun <R> handle(onSuccess: (T) -> R, onError: (Int, String) -> R) =
        if (code == 200) onSuccess.invoke(data!!) else onError.invoke(code, message!!)

    companion object {
        const val DEFAULE_MESSAGE = "Unknown Error"
        fun <T> ofSuccess(data: T) = Result(data = data)
        fun <T> ofError(serverException: ServerException) = Result<T>(
            code = serverException.code,
            message = serverException.message ?: DEFAULE_MESSAGE
        )
    }
}
