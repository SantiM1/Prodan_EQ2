package com.example.prodan

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.prodan.data.PetX
import com.example.prodan.databinding.FragmentDetailsBinding
import com.example.prodan.network.NetworkInterface
import com.example.prodan.network.RetrofitHelper
import com.example.prodan.network.UsersRequest
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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
        val preferences = this.activity?.getPreferences(Context.MODE_PRIVATE)
        val nombre = preferences?.getString("Nombre", "")
        val tel = preferences?.getString("Teléfono", "")
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.isVisible =false
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

            val retrofit = RetrofitHelper.getInstance().create(NetworkInterface:: class.java)
            binding.buttonSolicitud.setOnClickListener {
                lifecycleScope.launch {

                    val sdf = SimpleDateFormat("dd/M/yyyy")
                    val currentDate = sdf.format(Date())
                    val userReq =UsersRequest(petList.id, nombre.toString(), tel.toString(),  currentDate,  petList.img)
                    retrofit.addPetRequest(userReq)
                }
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("La solicitud fue enviada")
                    .setPositiveButton("Aceptar") { _, _ ->
                        Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_homeFragment)
                    }
                    .show()


            }

        }
        binding.closeBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_homeFragment)
        }

    }
}