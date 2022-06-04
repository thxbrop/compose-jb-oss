package retrofit

import Contracts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    inline fun <reified T> provide(): T =
        Retrofit.Builder()
            .baseUrl(Contracts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
}