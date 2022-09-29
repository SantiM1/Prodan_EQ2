package com.example.prodan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prodan.data.pet
import com.example.prodan.databinding.ItemPetBinding

class adapter (val context: Context, var data: List<pet>, private val funcionX : (pet) ->Unit ) :
    RecyclerView.Adapter<adapter.ViewHolder>()
{
    class ViewHolder(val binding: ItemPetBinding, funcionZ : (Int) ->Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener{
                funcionZ(absoluteAdapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPetBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view) {
            funcionX(data[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            imageViewPet.setImageResource(data[position].imagen)
            textViewNombre.text = data[position].nombre
        }

        /*
        holder.binding.textViewId.text = data[position].id
        holder.binding.textViewNombre.text = data[position].nombre*/

        /*Glide.with(context)
            .load(data[position].imagen_url)
            .into(holder.binding.imageView3)*/

    }

    override fun getItemCount(): Int {
        return data.size
    }
}