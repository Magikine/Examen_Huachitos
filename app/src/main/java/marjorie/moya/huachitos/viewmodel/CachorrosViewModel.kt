package marjorie.moya.huachitos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import marjorie.moya.huachitos.model.CachorrosResponse
import marjorie.moya.huachitos.model.db.CachorrosEntidad
import marjorie.moya.huachitos.model.network.ApiService
import marjorie.moya.huachitos.model.network.CachorrosDetalleResponse
import marjorie.moya.huachitos.model.network.RetrofitClass
import marjorie.moya.huachitos.viewmodel.ClaseApp.Companion.database
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CachorrosViewModel : ViewModel() {
    //Declaraciones de LiveData dependiendo de lo que nececite la vistas
    //LiveData para la pantalla de lista de los cachorros
    val listaCachorros = MutableLiveData<List<CachorrosEntidad>>()

    //LiveData para el detalle e una cachorros
    val detalleCachorros = MutableLiveData<CachorrosDetalleResponse>()

    //LiveData para los errores de la API
    val errores = MutableLiveData<String>()

    //funcion que va a ir a buscar la lista de cachorros a la API
    fun listarCachorros() {
        //Corrutina que va a ir a buscar la informacion
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Llamar API
                val retroIntancia = RetrofitClass.retrofit.create(ApiService::class.java)
                //quien va a llamar a la API
                val llamadaApi = retroIntancia.obtenerCachorros()
                //Llamar a la api para que nos devuelva los datos
                llamadaApi.enqueue(object : Callback<List<CachorrosResponse>> {
                    //Aca va la respuesta de la llamada de la API
                    //Metodo que se va a ejecutar si esta bien
                    override fun onResponse(
                        call: Call<List<CachorrosResponse>>, //la llamada a la API
                        response: Response<List<CachorrosResponse>> //La respuesta de la API
                    ) {
                        //vamos aver si la api me respondio bien
                        if (response.isSuccessful) {
                            val respuesta = response.body()
                            //Lista que vamos a enviar a la Base de datos local
                            val listaCachorrosMapeada = ArrayList<CachorrosEntidad>()
                            if (respuesta != null) {
                                for (cachorros in respuesta) {
                                    val cachorrosAGuardar = CachorrosEntidad(
                                        id_appi = cachorros.id_appi,
                                        nombre = cachorros.nombre,
                                        edad = cachorros.edad,
                                        imagen = cachorros.imagen,
                                        region = cachorros.region,
                                        tipo = cachorros.tipo,
                                        color = cachorros.color,
                                        logo = cachorros.logo,
                                        estado = cachorros.estado,
                                        genero = cachorros.genero,
                                        desc_fisica = cachorros.desc_fisica,
                                        desc_personalidad = cachorros.desc_fisica,
                                        desc_adicional = cachorros.desc_adicional,
                                        esterilazado = cachorros.esterilazado,
                                        vacunas = cachorros.vacunas,
                                        equipo = cachorros.equipo,
                                        comuna = cachorros.comuna
                                    )



                                    //Agrego la empresa a la lista que voy a enviar a la base de datos
                                    listaCachorrosMapeada.add(cachorrosAGuardar)
                                }
                                GlobalScope.launch {
                                    //Vamos a vaciar la base de datos antes de insertar la nueva data
                                    database.cachorrosDao().borrarDB()
                                    //Vamos a hacer la insercion
                                    database.cachorrosDao().insertarData(listaCachorrosMapeada)
                                    //Vamos a obtener las empresas
                                    listaCachorros.postValue(
                                        database.cachorrosDao().obeterEmpresasDB()
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
                    override fun onFailure(call: Call<List<CachorrosResponse>>, t: Throwable) {
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


    /**
     * Funcion que va a a llamar a la segunda API
     */
    fun obtenerDetalleEmpresa(idEmpresa: Int) {
        //Corrutina que va a ir a buscar la informacion
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Llamar API
                val retroIntancia = RetrofitClass.retrofit.create(ApiService::class.java)
                //quien va a llamar a la API
                val llamadaApi = retroIntancia.detalleEmpresa(idEmpresa)
                //Llamar a la api para que nos devuelva los datos
                llamadaApi.enqueue(object : Callback<CachorrosDetalleResponse> {
                    //Aca va la respuesta de la llamada de la API
                    //Metodo que se va a ejecutar si esta bien
                    override fun onResponse(
                        call: Call<CachorrosDetalleResponse>, //la llamada a la API
                        response: Response<CachorrosDetalleResponse> //La respuesta de la API
                    ) {
                        //vamos aver si la api me respondio bien
                        if (response.isSuccessful) {
                            val respuesta = response.body()
                            detalleCachorros.postValue(respuesta)
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
                    override fun onFailure(call: Call<CachorrosDetalleResponse>, t: Throwable) {
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
