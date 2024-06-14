package marjorie.moya.huachitos.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Dao que va a implementar todos los metodos que vamos a usar en esta app
 */
@Dao
interface AnimalDao {

    //Metodo para insertar toda la data
    @Insert
    fun insertarData(animales : List<AnimalEntidad>)

    //Metodo para traer toda la informacion
    @Query("Select * from animales")
    fun obeterAnimalesDB() : List<AnimalEntidad>

    //Metodo para boorar toda la data
    @Query("DELETE FROM animales")
    fun borrarDB()
}