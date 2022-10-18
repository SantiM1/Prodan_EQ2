package com.example.prodan

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prodan.data.PetX
import com.example.prodan.data.pet
import com.example.prodan.databinding.ItemPetBinding



class AdapterHome (val context: Context, var data: pet, private val funcionX : (PetX) ->Unit) :
    RecyclerView.Adapter<AdapterHome.ViewHolder>()
{


    class ViewHolder(val binding: ItemPetBinding,data: pet,  funcionZ : (Int) -> Unit, ):
        RecyclerView.ViewHolder(binding.root) {

        init {

            itemView.setOnClickListener{
                data.pets[absoluteAdapterPosition].click = 0
                funcionZ(absoluteAdapterPosition)

            }
            binding.imageButton.setOnCheckedChangeListener{ _, isChecked->
                data.pets[absoluteAdapterPosition].click = 1
                if(isChecked){
                    data.pets[absoluteAdapterPosition].fav = 1
                }else{
                    data.pets[absoluteAdapterPosition].fav = 0
                }
                funcionZ(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemPetBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view, data) {
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

        if(data.pets[position].fav == 1){
            holder.binding.imageButton.isChecked = true
        }



    }

    override fun getItemCount(): Int {
        return data.pets.size
    }
}