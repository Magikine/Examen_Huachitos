package marjorie.moya.huachitos.model.network

import marjorie.moya.huachitos.model.CachorrosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //Listado de empresas
    @GET("empresas")
    fun  obtenerEmpresas() : Call<List<CachorrosResponse>>

    //Detalle de empresas
    @GET("empresas/{id_empresa}") //https://retoolapi.dev/cluuwe/empresas/9
    fun detalleEmpresa(@Path("id_empresa") empresaCargar : Int): Call<CachorrosDetalleResponse>
}


