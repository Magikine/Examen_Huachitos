package marjorie.moya.huachitos

import android.app.Application
import androidx.room.Room


class ClaseApp : Application() {

    companion object {
        //Base de datos
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        // Configurar la base de datos Room
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "bd_animales"
        ).build()
    }
}