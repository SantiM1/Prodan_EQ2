package com.example.prodan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prodan.user.database.GalleryImg
import com.example.prodan.databinding.PetImgsListBinding

class GalleryImgAdapter(
     var galleryImg: List<GalleryImg>,
     private val funcionX: (GalleryImg) ->Unit ) :
    RecyclerView.Adapter<GalleryImgAdapter.ViewHolder>()
{
    class ViewHolder(val binding: PetImgsListBinding, funcionZ: (Int) -> Unit  ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener{
                funcionZ(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = PetImgsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view) {
            funcionX(galleryImg[it])
        }


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {

            titleTextview.text = galleryImg[position].title
            descTextview.text = galleryImg[position].desc
            //img glide

        }

    }
    override fun getItemCount(): Int {
        return galleryImg.size
    }
}