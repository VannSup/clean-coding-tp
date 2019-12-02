package fr.vannsup.tp.data.networking

import fr.vannsup.tp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private class HttpClientManagerImpl : HttpClientManager {

    private val okHttpClient = OkHttpClient
        .Builder()
        .apply {
            if (BuildConfig.DEBUG)
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        this.level = HttpLoggingInterceptor.Level.BODY
                    }
                )
        }
        .build()

    override val retrofit: Retrofit = Retrofit
        .Builder()
        // base URL Stoker dans le gradle app ligne 14 : buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

interface HttpClientManager {

    val retrofit: Retrofit

    companion object {

        val instance: HttpClientManager = HttpClientManagerImpl()

    }
}

// T est abstrait il peut etre remplacer par n'imp le reified permet de ne pas mettre de type api car il est refleter
inline fun <reified T> HttpClientManager.createApi(): T {

    return retrofit.create()
}