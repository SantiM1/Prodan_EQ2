package com.example.prodan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cloudinary.android.MediaManager
import com.example.prodan.databinding.FavouriteItemBinding
import com.example.prodan.user.database.Favourite

class FavouriteAdapter(
    var favourite: List<Favourite>,
    private val funcionX: (Favourite) ->Unit ) :
    RecyclerView.Adapter<FavouriteAdapter.ViewHolder>()
{
    class ViewHolder(val binding: FavouriteItemBinding, funcionZ: (Int) -> Unit  ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener{
                funcionZ(absoluteAdapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = FavouriteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view) {
            funcionX(favourite[it])
        }


    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            textViewNombre.text = favourite[position].name


        }
        Glide.with(holder.itemView)
            .load(favourite[position].imgUrl)
            .into(holder.binding.imageViewPet)



    }
    override fun getItemCount(): Int {
        return favourite.size
    }
}

