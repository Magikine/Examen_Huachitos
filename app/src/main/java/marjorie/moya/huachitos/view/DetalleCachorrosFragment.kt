package marjorie.moya.huachitos.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import marjorie.moya.huachitos.databinding.FragmentDatalleCachorroBinding
import marjorie.moya.huachitos.viewmodel.CachorrosViewModel

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ID_CACHORRO = "idCachorro"

/**
 * A simple [Fragment] subclass.
 * Use the [DetalleCachorroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class DetalleCachorrosFragment: Fragment() {
    private var idcachorros: Int = 0

    private var _binding: FragmentDatalleCachorroBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idcachorros = it.getInt(ID_CACHORRO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDatalleCachorroBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Configuraciones
         */
        //Configurar el ViewModel
        var viewModelCachorros = ViewModelProvider(this).get(CachorrosViewModel::class.java)
        //Configurar el Observador
        viewModelCachorros.detalleCachorros.observe(
            viewLifecycleOwner
        ) { datosCachorros ->
            binding.txtnombe.text = datosCachorros.nombre
            binding.txtregion.text = datosCachorros.region
            Picasso.get().load(datosCachorros.logo).into(binding.imagenLogo)
        }
        viewModelCachorros.errores.observe(viewLifecycleOwner) {
            //
        }
        //Configurar el click del boton
        binding.correo.setOnClickListener {


            //Email
            val  emailIntent: Intent =Intent(Intent.ACTION_SEND)
            emailIntent.setType("text/html")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, "ventas_botxcamps@gmail.com")
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "I'm email body.")
            startActivity(Intent.createChooser(emailIntent, "Send Email"))

            //Enviar Email
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // solo las aplicaciones de correo deben manejar esto
                putExtra(Intent.EXTRA_EMAIL, arrayOf("ventas_botxcamps@gmail.com"))
                putExtra(Intent.EXTRA_SUBJECT, "Consulta  Choli Id 197")
            }
            startActivity(intent)
        }
        //Ejecucion desde el ViewModel
        viewModelCachorros.obtenerDetalleCachorros(idcachorros)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param idCachorro Como el Id del cachorro.
         * @return A new instance of fragment DatalleCachorroFragment.
         */
        @JvmStatic
        fun newInstance(idCachorros: Int) =
            DetalleCachorrosFragment().apply {
                arguments = Bundle().apply {
                    putInt(ID_CACHORRO, idcachorros)
                }
            }
    }
}