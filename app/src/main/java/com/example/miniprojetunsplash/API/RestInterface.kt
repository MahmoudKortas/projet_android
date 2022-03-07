package com.example.myapplicationtp5555.API
import com.example.myapplicationtp5555.models.UnsplashPhoto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestInterface {
    @GET("photos/random/?client_id=i829F_stpGuvWAYkcgovElg9eCVVZRLsQwmDh2svmw0&count=16")
    //@Headers("Content-Type:application/json","Authorization:i829F_stpGuvWAYkcgovElg9eCVVZRLsQwmDh2svmw0 jdRH9CpHEnQiDIyNSr51kDYePZn2gjWdo43KOKMMCEc")
    //@GET("photos/random")
    fun getHeroes(): Call<List<UnsplashPhoto>> //End Url
}
class RetrofitInstance2 {
    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"//photos/random/?client_id=i829F_stpGuvWAYkcgovElg9eCVVZRLsQwmDh2svmw0&count=16"
        private val interceptor: HttpLoggingInterceptor =HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        private val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        fun getRetrofitInstance2(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
