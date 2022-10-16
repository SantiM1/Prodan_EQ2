package com.example.prodan

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudinary.Search
import com.example.prodan.data.PetX
import com.example.prodan.data.pet
import com.example.prodan.databinding.FragmentHomeBinding
import com.example.prodan.network.PetRetriever
import com.example.prodan.user.database.Favourite
import kotlinx.coroutines.*

class HomeFragment : Fragment() {
    //Pasar lo de API Ejemplo Aquí

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var petList: List<PetX> = emptyList()
    
    private val evm : FavouriteViewModel by viewModels {
        FavouriteViewModelFactory((activity?.application as ProdanApp).repositoryFavourite)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val menuItem = menu.findItem(R.id.searchView)
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredPets: ArrayList<PetX> = ArrayList()
                val query = newText!!.toString().lowercase()

                petList.forEach {
                    if(it.name.lowercase().contains(query) || it.id.toString().lowercase().contains(query))
                        filteredPets.add(it)
                }

                val pet = pet(filteredPets.toList())
                renderData(pet)

                return false
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
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
            renderData(petResponse.pets)
        }
    }

    /*private fun renderData(petResponse: pet) {
        // Enviamos la data al adaptador
        binding.rvpet.adapter = adapter(requireActivity(), petResponse){
            petList = petResponse.pets

            petResponse.pets.filter{ evm.getFavouriteWName(it.name) > 0}.forEach{
                it.fav = 1

            }

            //render data in RecyclerView
            renderData(petResponse)

        }
    }

    private fun renderData(petResponse: pet) {
        binding.rvpet.adapter = AdapterHome(requireActivity(), petResponse) {
            val bundle = Bundle()
            bundle.putParcelable("pet", it)
            if(it.fav == 1){
                if(evm.getFavouriteWName(it.name) == 0) {
                    evm.addFavourite(Favourite(it.name, it.fav, it.img))
                }
            }else{
                evm.deleteFavouriteWName(it.name)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
        }
    }

    private fun initRecyclerView() {
        // Creamos el layout manager
        binding.rvpet.layoutManager = GridLayoutManager(requireActivity(),
            2, RecyclerView.VERTICAL, false)
    }
}