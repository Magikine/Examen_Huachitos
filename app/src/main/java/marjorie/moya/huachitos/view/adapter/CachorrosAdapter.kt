package marjorie.moya.huachitos.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import marjorie.moya.huachitos.R
import marjorie.moya.huachitos.databinding.FilaListaCachorrosBinding
import marjorie.moya.huachitos.model.db.CachorrosEntidad
import marjorie.moya.huachitos.view.DetalleCachorrosFragment


class CachorrosAdapter(private val listaEmpresas: List<CachorrosEntidad>) :
    RecyclerView.Adapter<CachorrosAdapter.EmpresaViewHolder>() {


    class EmpresaViewHolder(val binding: FilaListaCachorrosBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpresaViewHolder {
        val binding =
            FilaListaCachorrosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmpresaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listaCachorros

    }

    override fun onBindViewHolder(holder: EmpresaViewHolder, position: Int) {
        val empresa = listaCachorro[position]
        holder.binding.txtnombe.text = cachorro.nombre
        holder.binding.txtedad.text = cachorro.edad
        holder.binding.txtregion.text = cachorro.region
        //Imagen
        Picasso.get()
            .load(empresa.url_logo)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.imagenLogo)
        //Configurar el click
        holder.binding.root.setOnClickListener {
            var detalle = DetalleCachorrosFragment.newInstance(cachorro.id_api)
            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.main, detalle)
                .addToBackStack(null).commit()
        }
    }

}