package com.example.prodan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prodan.data.pet
import com.example.prodan.databinding.FragmentHomeBinding
import com.example.prodan.network.PetRetriever
import kotlinx.coroutines.*

class HomeFragment : Fragment() {
    //Pasar lo de API Ejemplo Aquí

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    // Creamos una instancia de petRetriever
    private val petRetriever : PetRetriever = PetRetriever()

    // Cuando se crea el view
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflamos el layout del Fragment
        return binding.root
    }

    // Cuando el view ha sido creado
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Tomamos la información del API
        fetchPets()
        // Insertamos la información en el RecyclerView
        initRecyclerView()
    }

    private fun fetchPets() {
        // Creamos el Job que utilizará la corrutina
        val petsFetchJob = Job()
        // Iniciamos el ErrorHandler
        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
        }
        // Creamos el error para la corrutina
        val scope = CoroutineScope(petsFetchJob + Dispatchers.Main)
        scope.launch(errorHandler){
            //obtenemos el data con el petRetriever
            val petResponse = petRetriever.getPets()
            //insertamos el data en el RecyclerView
            renderData(petResponse)
        }
    }

    private fun renderData(petResponse: pet) {
        // Enviamos la data al adaptador
        binding.rvpet.adapter = adapter(requireActivity(), petResponse){
            val bundle = Bundle()
            bundle.putParcelable("pet",it)
        }
    }

    private fun initRecyclerView() {
        // Creamos el layout manager
        binding.rvpet.layoutManager = GridLayoutManager(requireActivity(),
            2, RecyclerView.VERTICAL, false)
    }
}