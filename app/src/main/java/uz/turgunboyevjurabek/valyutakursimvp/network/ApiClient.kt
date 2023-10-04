package uz.turgunboyevjurabek.valyutakursimvp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.turgunboyevjurabek.valyutakursimvp.Cantract.Cantrakt

object ApiClient {
    const val BASE_URL= "https://cbu.uz/uz/arkhiv-kursov-valyut/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }
    fun getApiServis():Cantrakt.Model.apiServis{
        return getRetrofit().create(Cantrakt.Model.apiServis::class.java)
    }
}