package marjorie.moya.huachitos.viewmodel

import androidx.room.Room
import marjorie.moya.huachitos.model.db.AppDatabase

class ClaseApp {
    companion object {
        //Base de datos
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // Configurar la base de datos Room
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "bd_cachorros"
        ).build()
    }
}
