package marjorie.moya.huachitos.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import marjorie.moya.huachitos.R
import marjorie.moya.huachitos.model.db.AnimalEntidad
import marjorie.moya.huachitos.view.DetalleAnimalFragment
import marjorie.moya.huachitos.databinding.FilaListaAnimalBinding
class AnimalAdapter (private val listaAnimal: List<AnimalEntidad>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>(){

    class AnimalViewHolder(val binding: FilaListaAnimalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimalViewHolder {
        val binding =
            FilaListaAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = listaAnimal[position]
        holder.binding.txtnombe.text = animal.nombre
        holder.binding.txtedad.text = animal.tipo
        holder.binding.txtregion.text = animal.color
        //Imagen
        Picasso.get()
            .load(animal.imagen)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.imagenLogo)
        //Configurar el click
        holder.binding.root.setOnClickListener {
            var detalle = DetalleAnimalFragment.newInstance(animal.id)
            val activity = it.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(R.id.main, detalle)
                .addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return listaAnimal.size
    }


}