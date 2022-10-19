package com.example.prodan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.prodan.data.PetX
import com.example.prodan.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {


    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        // Inflamos el layout del Fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            val petList = it.get("pet") as PetX

            //val petList = it.getParcelable("pet",PetX)
            binding.textViewDetallesNombre.text = petList.name
            binding.textViewDetallesCustodia.text = petList.custodia
            binding.textViewDetallesEdad.text = petList.edad
            binding.textViewDetallesRaza.text = petList.raza
            binding.textViewdetallesTamaO.text = petList.tamaño

            Glide.with(this)
                .load(petList.img)
                .into(binding.imageViewDetalles)

        }
        binding.closeBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_homeFragment)
        }
    }
}