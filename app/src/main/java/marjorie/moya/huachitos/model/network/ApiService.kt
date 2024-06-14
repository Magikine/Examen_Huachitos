package marjorie.moya.huachitos.model.network


import marjorie.moya.huachitos.model.AnimalDetailModel
import marjorie.moya.huachitos.model.AnimalModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Interfaz que va a tener las Apis que vamos a usar
 */
interface ApiService {
    //Listado de animales
    @GET("animales")
    fun obtenerAnimales(): Call<List<AnimalModel>>

    //Detalle de animales
    @GET("animales/{id_animal}") //https://caso-perritos-adopcion-c74apk0pl-talento-projects.vercel.app/animales/228
    fun detalleAnimal(@Path("id_animal") animalCargar: Int): Call<AnimalDetailModel>
}