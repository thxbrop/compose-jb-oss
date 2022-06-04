import retrofit.RetrofitProvider

abstract class ViewModel {

    companion object {
        @JvmStatic
        protected inline fun <reified T> provideService() = RetrofitProvider.provide<T>()
    }
}