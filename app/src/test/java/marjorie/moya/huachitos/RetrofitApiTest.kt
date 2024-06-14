package marjorie.moya.huachitos



import org.junit.Test

/**
 * Probando la Api de Retrofit
 */
class RetrofitApiTest {
    //Prueba que va a llamar al detalle de la empresa
    @Test
    fun testPlacesService() {
        //Get an instance of PlacesService by proiving the Retrofit instance
        val service = RetrofitClass.retrofit.create(ApiService::class.java)
        //Execute the API call
        val response = service.detalleEmpresa(1).execute()
        //Check for error body
        val errorBody = response.errorBody()
        assert(errorBody == null)
        //Check for success body
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
    }


}