package marjorie.moya.huachitos.model.network

import marjorie.moya.huachitos.model.CachorrosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //Listado de cachorros
    @GET("cachorros")
    fun  obtenerCachorros() : Call<List<CachorrosResponse>>

    //Detalle de cachorros
    @GET("cachorros/{id}") //https://caso-perritos-adopcion-c74apk0pl-talento-projects.vercel.app/animales
    fun detalleCachorros(@Path("id") cachorroCargar : Int): Call<CachorrosDetalleResponse>

}


