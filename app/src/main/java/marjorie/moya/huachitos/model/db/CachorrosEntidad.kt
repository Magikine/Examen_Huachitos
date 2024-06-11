package marjorie.moya.huachitos.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad de la base de datos que vamos a crear
 */
@Entity(tableName = "cachorros")
data class CachorrosEntidad(
    //aca van los campos que voy a guardar en la base de datos
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val tipo: String,
    val color: String,
    val edad: String,
    val logo: String,
    val estado: String,
    val id_appi: String,
    val genero: String,
    val desc_fisica: String,
    val desc_personalidad: String,
    val desc_adicional: String,
    val esterilazado: Boolean,
    val vacunas: String,
    val imagen: String,
    val equipo: String,
    val region: String,
    val comuna: String,
    val url_Cachorro: String
){
    override fun toString(): String {
        return this.nombre
    }
}
