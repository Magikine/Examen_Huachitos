package marjorie.moya.huachitos.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import marjorie.moya.huachitos.viewmodel.AnimalViewModel


private const val ID_ANIMAL = "idAnimal"
/**
 * A simple [Fragment] subclass.
 * Use the [DetalleAnimalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetalleAnimalFragment : Fragment() {

    private var idAnimal: Int = 0
//    private lateinit var nombreAnimal: String

    private var _binding: FragmentDetalleAnimalBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idAnimal = it.getInt(ID_ANIMAL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleAnimalBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Configuraciones
         */
        //Configurar el ViewModel
        var viewmodelAnimal = ViewModelProvider(this).get(AnimalViewModel::class.java)
        //Configurar el Observador
        viewmodelAnimal.detalleAnimales.observe(
            viewLifecycleOwner
        ) { datosAnimal ->
            binding.txtNombreDetalle.text = datosAnimal.nombre
            binding.txtTipoDetalle.text = datosAnimal.tipo
            Picasso.get().load(datosAnimal.imagen).into(binding.imgAnimalDetalle)
        }
        viewmodelAnimal.errores.observe(viewLifecycleOwner) {
            //
        }
        //Configurar el click del boton
        binding.enviar.setOnClickListener {
//            enviarCorreo(nombreAnimal, idAnimal)}

            // Ejecución desde el ViewModel

            /* Abrir URL
                val url = "http://www.google.com"
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(url))
                startActivity(i)

                //Compartir algo
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT, "Hello, this is some text to share.")
                startActivity(Intent.createChooser(intent, "Share via"))

                //Email
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/html")
                intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.")
                startActivity(Intent.createChooser(intent, "Send Email"))*/


            //Configurar el click del boton
//            binding.mailButton.setOnClickListener {
//
//                //Enviar Email
//                // Configurar el intent para enviar el correo y en el asunto se incluya el nombre del producto
//                val intent = Intent(Intent.ACTION_SENDTO).apply {
//                    val uri = Uri.parse("contacto@xxxx.com?subject=Me%20interesa%20el%20producto:%20$nombreEmpresa")
//                    data = uri  // Especifica el correo y el asunto
            //Enviar Email
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data =
                    Uri.parse("mailto:ventas_botxcamps@gmail.com") // solo las aplicaciones de correo deben manejar esto
                putExtra(Intent.EXTRA_EMAIL, arrayOf("ventas_botxcamps@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Consulta   $idAnimal")
            }
            startActivity(intent)
        }
        //Ejecucion desde el ViewModel
        viewmodelAnimal.obtenerDetalleAnimal(idAnimal)
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param idAnimal Como el Id de la empresa.
         * @return A new instance of fragment DatalleEmpresaFragment.
         */
        @JvmStatic
        fun newInstance(idAnimal: Int) =
            DetalleAnimalFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID_ANIMAL, idAnimal)
                }
            }
    }
}