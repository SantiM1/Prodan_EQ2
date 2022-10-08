package com.example.prodan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prodan.data.PetX
import com.example.prodan.data.pet
import com.example.prodan.databinding.ItemPetBinding


class adapter (val context: Context, var data: pet, private val funcionX : (PetX) ->Unit ) :
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
            funcionX(data.pets[it])
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textViewNombre.text = data.pets[position].name
        }


        Glide.with(context)
            .load(data.pets[position].img)
            .into(holder.binding.imageViewPet)


    }

    override fun getItemCount(): Int {
        return data.pets.size
    }
}