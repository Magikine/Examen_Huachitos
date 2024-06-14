package marjorie.moya.huachitos.model

data class AnimalDetailModel(
    val id: Int,
    val nombre: String?,
    val tipo: String?,
    val color: String?,
    val edad: String?,
    val estado: String?,
    val genero: String?,
    val desc_fisica: String?,
    val desc_personalidad: String?,
    val desc_adicional: String?,
    val esterilizado: Boolean?,
    val vacunas: Int?,
    val imagen: String?,
    val equipo: String?,
    val region: String?,
    val comuna: String?
)
