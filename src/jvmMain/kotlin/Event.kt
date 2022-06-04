data class Event<out T>(
    private val data: T?,
    var isHandled: Boolean = false
) {
    fun takeIfNotHandled(handler: (T) -> Unit) {
        if (!isHandled) {
            isHandled = true
            handler.invoke(data!!)
        }
    }

    companion object {
        fun <T> empty() = Event<T>(
            data = null,
            isHandled = true
        )
    }
}
