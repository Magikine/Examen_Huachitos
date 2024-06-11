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


class CachorrosAdapter(private val listaDetalleCachorros: List<CachorrosEntidad>) :
    RecyclerView.Adapter<CachorrosAdapter.CachorroViewHolder>() {


    class CachorroViewHolder(val binding: FilaListaCachorrosBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CachorroViewHolder {
        val binding =
            FilaListaCachorrosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CachorroViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val listaCachorros = 0
        return listaCachorros

    }

    override fun onBindViewHolder(holder: CachorroViewHolder, position: Int) {
        val cachorros   = listaDetalleCachorros[position]
        holder.binding.txtnombe.text = cachorros.nombre
        holder.binding.txtedad.text = cachorros.edad
        holder.binding.txtregion.text = cachorros.region
        //Imagen
        Picasso.get()
            .load(cachorros.url_Cachorro)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.imagenLogo)
        //Configurar el click
        holder.binding.root.setOnClickListener {
            var detalle = DetalleCachorrosFragment.newInstance(cachorros.id)
            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.main, detalle)
                .addToBackStack(null).commit()
        }
    }

}