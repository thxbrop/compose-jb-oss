data class ServerException(
    override val message: String,
    val code: Int
) : RuntimeException(message) {
    companion object {

    }
}