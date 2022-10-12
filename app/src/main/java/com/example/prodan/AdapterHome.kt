package com.example.prodan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prodan.data.PetX
import com.example.prodan.data.pet
import com.example.prodan.databinding.ItemPetBinding
import com.example.prodan.user.database.Favourite
import com.example.prodan.user.database.ProdanDatabase
import kotlinx.coroutines.CoroutineScope
import kotlin.contracts.contract


class AdapterHome (val context: Context, var data: pet, private val funcionX : (PetX) ->Unit) :
    RecyclerView.Adapter<AdapterHome.ViewHolder>()
{



    class ViewHolder(val binding: ItemPetBinding, funcionZ : (Int) ->Unit, ) :
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

        holder.binding.imageButton.setOnClickListener{
            val name = data.pets[position].name
            val desc = ""
            val img = data.pets[position].img
            val favourite = Favourite(name, desc, img)


            //evm.addFavourite(favourite)

        }
    }

    override fun getItemCount(): Int {
        return data.pets.size
    }
}