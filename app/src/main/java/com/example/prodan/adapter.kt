package com.example.prodan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prodan.data.PetX
import com.example.prodan.databinding.ItemPetBinding


class adapter (val context: Context, var data: List<PetX>, private val funcionX : (PetX) ->Unit ) :
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
            textViewNombre.text = data[position].name
        }


        Glide.with(context)
            .load(data[position].img)
            .into(holder.binding.imageViewPet)


    }

    override fun getItemCount(): Int {
        return data.size
    }
}