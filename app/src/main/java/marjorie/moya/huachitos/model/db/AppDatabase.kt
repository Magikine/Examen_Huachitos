package marjorie.moya.huachitos.model.db

import androidx.room.Database
import androidx.room.RoomDatabase


/**
 * Aca se configura la Base de datos
 */
@Database(entities = [AnimalEntidad::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
     //Aca le digo que Dao vamos a usar
     abstract fun animalDao() : AnimalDao
}