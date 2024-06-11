package marjorie.moya.huachitos.model

/**
 * Data que vamos  a recibir de la Api
 */

data class CachorrosDetalleResponse(
    //Se agregan todas las variables que nos va a traer la Api
    val id :Int,
    val nombre:String,
    val tipo: String,
    val color :String,
    val edad: String,
    val logo : String,
    val estado :String,
    val id_appi : String,
    val genero : String,
    val desc_fisica : String,
    val desc_personalidad : String,
    val desc_adicional : String,
    val esterilazado : Boolean,
    val vacunas : String,
    val imagen : String,
    val equipo: String,
    val region : String,
    val comuna : String
)





