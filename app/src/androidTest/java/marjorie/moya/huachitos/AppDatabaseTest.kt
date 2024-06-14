package marjorie.moya.huachitos



import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import marjorie.moya.huachitos.model.db.AnimalEntidad
import marjorie.moya.huachitos.model.db.AppDatabase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith




@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var miBaseDeDatos: AppDatabase

    @Before
    fun setup() {
        // Crear una instancia de la base de datos de prueba
        miBaseDeDatos = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDown() {
        // Cerrar la base de datos después de cada prueba
        miBaseDeDatos.close()
    }
}

   /** @Test
    fun testInsertarDataRecuperar() {
        // Insertar datos en la base de datos
        val datos = ArrayList<AnimalEntidad>()
        datos.add(
            AnimalEntidad(
                id = "197",
                nombre = "cholita",
                ("https://caso-perritos-adopcion-c74apk0pl-talento-projects.vercel.app/")
            )
        )

        miBaseDeDatos.AnimalDao().insertarData(datos)
        // Recuperar el dato insertado
        val datoRecuperado =
            miBaseDeDatos.AnimalDao().obeterCachorrosDB()
        // Verificar que el dato recuperado sea el mismo que elinsertado
        assertEquals("cholita", datoRecuperado[0].nombre)
    }**/

