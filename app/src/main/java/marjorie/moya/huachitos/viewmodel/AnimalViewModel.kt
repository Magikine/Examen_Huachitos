package marjorie.moya.huachitos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



import marjorie.moya.huachitos.ClaseApp.Companion.database
import marjorie.moya.huachitos.model.AnimalDetailModel
import marjorie.moya.huachitos.model.AnimalModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import marjorie.moya.huachitos.model.db.AnimalEntidad
import marjorie.moya.huachitos.model.network.ApiService
import marjorie.moya.huachitos.model.network.RetrofitClass


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Viwe model que va a traer la lista de datos y el detalle de la empresa
 */
class AnimalViewModel : ViewModel() {
    //Declaraciones de LiveData dependiendo de lo que nececite la vistas
    //LiveData para la pantalla de lista de empresas
    val listaAnimales = MutableLiveData<List<AnimalEntidad>>()

    //LiveData para el detalle e una empresa
    val detalleAnimales = MutableLiveData<AnimalDetailModel>()

    //LiveData para los errores de la API
    val errores = MutableLiveData<String>()

    //funcion que va a ir a buscar la lista de empresas a la API
    fun listarAnimales() {
        //Corrutina que va a ir a buscar la informacion
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Llamar API
                val retroIntancia = RetrofitClass.retrofit.create(ApiService::class.java)
                //quien va a llamar a la API
                val llamadaApi = retroIntancia.obtenerAnimales()
                //Llamar a la api para que nos devuelva los datos
                llamadaApi.enqueue(object : Callback<List<AnimalModel>> {
                    //Aca va la respuesta de la llamada de la API
                    //Metodo que se va a ejecutar si esta bien
                    override fun onResponse(
                        call: Call<List<AnimalModel>>, //la llamada a la API
                        response: Response<List<AnimalModel>> //La respuesta de la API
                    ) {
                        //vamos aver si la api me respondio bien
                        if (response.isSuccessful) {
                            val respuesta = response.body()
                            //Lista que vamos a enviar a la Base de datos local
                            val listaAnimalMapeada = ArrayList<AnimalEntidad>()
                            if (respuesta != null) {
                                for (animal in respuesta) {
                                    val animalAGuardar = AnimalEntidad(
                                        id = animal.id,
                                        nombre = animal.nombre,
                                        tipo = animal.tipo,
                                        color = animal.color,
                                        edad = animal.edad,
                                        estado = animal.estado,
                                        genero = animal.genero,
                                        desc_fisica = animal.desc_fisica,
                                        desc_personalidad = animal.desc_personalidad,
                                        desc_adicional = animal.desc_adicional,
                                        esterilizado = animal.esterilizado,
                                        vacunas = animal.vacunas,
                                        imagen = animal.imagen,
                                        equipo = animal.equipo,
                                        region = animal.region,
                                        comuna = animal.comuna,

//
                                    )
                                    //Agrego la empresa a la lista que voy a enviar a la base de datos
                                    listaAnimalMapeada.add(animalAGuardar)
                                }
                                GlobalScope.launch {
                                    //Vamos a vaciar la base de datos antes de insertar la nueva data
                                    database.animalDao().borrarDB()
                                    //Vamos a hacer la insercion
                                    database.animalDao().insertarData(listaAnimalMapeada)
                                    //Vamos a obtener las empresas
                                    listaAnimales.postValue(
                                        database.animalDao().obeterAnimalesDB()
                                    )
                                }
                            }
                        } else {
                            //Mostrar mensaje de error
                            errores.postValue(
                                "Error En la API - ${
                                    response.errorBody().toString()
                                }"
                            )
                        }
                    }

                    //Metodo que se va a ejecuar si hay algun error
                    override fun onFailure(call: Call<List<AnimalModel>>, t: Throwable) {
                        //Mostrar mensaje de error
                        errores.postValue("Error De Falla - ${t.message}")
                    }

                })
            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                e.printStackTrace()
                errores.postValue("Error Interno - ${e.message}")
            }
        }
    }
//

    /**
     * Funcion que va a a llamar a la segunda API
     */
    fun obtenerDetalleAnimal(idAnimal: Int) {
        //Corrutina que va a ir a buscar la informacion
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Llamar API
                val retroIntancia = RetrofitClass.retrofit.create(ApiService::class.java)
                //quien va a llamar a la API
                val llamadaApi = retroIntancia.detalleAnimal(idAnimal)
                //Llamar a la api para que nos devuelva los datos
                llamadaApi.enqueue(object : Callback<AnimalDetailModel> {
                    //Aca va la respuesta de la llamada de la API
                    //Metodo que se va a ejecutar si esta bien
                    override fun onResponse(
                        call: Call<AnimalDetailModel>, //la llamada a la API
                        response: Response<AnimalDetailModel> //La respuesta de la API
                    ) {
                        //vamos aver si la api me respondio bien
                        if (response.isSuccessful) {
                            val respuesta = response.body()
                            detalleAnimales.postValue(respuesta)
                        } else {
                            //Mostrar mensaje de error
                            errores.postValue(
                                "Error En la API - ${
                                    response.errorBody().toString()
                                }"
                            )
                        }
                    }

                    //Metodo que se va a ejecuar si hay algun error
                    override fun onFailure(call: Call<AnimalDetailModel>, t: Throwable) {
                        //Mostrar mensaje de error
                        errores.postValue("Error De Falla - ${t.message}")
                    }

                })
            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                e.printStackTrace()
                errores.postValue("Error Interno - ${e.message}")
            }

        }
    }
}