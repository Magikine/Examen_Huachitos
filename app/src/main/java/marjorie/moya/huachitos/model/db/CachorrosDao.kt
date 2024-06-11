package marjorie.moya.huachitos.model.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Dao que va a implementar todos los metodos que vamos a usar en esta app
 */
@Dao
interface CachorrosDao {

    //Metodo para insertar toda la data
    @Insert
    fun insertarData(cachorros : List<CachorrosEntidad>)

    //Metodo para traer toda la informacion
    @Query("Select * from cachorros")
    fun obeterCachorrosDB() : List<CachorrosEntidad>

    //Metodo para borrar toda la data
    @Query("DELETE FROM cachorros")
    fun borrarDB()
}