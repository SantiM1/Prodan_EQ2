package com.example.prodan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.prodan.databinding.FragmentGalleryUploadBinding
import com.example.prodan.user.database.GalleryImg



class GalleryUploadFragment : Fragment() {

    private var _binding: FragmentGalleryUploadBinding ? = null
    private val binding get() = _binding!!


    private val evm : GalleryImgViewModel by viewModels {
        GalleryImgViewModelFactory((activity?.application as ProdanApp).repositoryGallery)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryUploadBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.UploadBtn.setOnClickListener{
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()
            //val img = glide

            val galleryImg = GalleryImg(title, description, imgUrl = "")

            evm.addGalleryImg(galleryImg)

            Navigation.findNavController(view).navigate(R.id.action_galleryUploadFragment_to_userFragment)
        }
    }

}