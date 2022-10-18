package com.example.prodan

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private val petRetriever : PetRetriever = PetRetriever()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchComments()
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

    private fun fetchComments() {
        val petsFetchJob = Job()

        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
        }

        val scope = CoroutineScope(petsFetchJob + Dispatchers.Main)
        scope.launch(errorHandler){

            //fetch data
            val petResponse = petRetriever.getPets()
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
                    evm.addFavourite(Favourite(it.name, it.fav, it.img, it.raza, it.edad, it.tamaño, it.custodia))
                }
            }else {
                evm.deleteFavouriteWName(it.name)
            }
           if(Navigation.findNavController((requireView())).currentDestination?.id == R.id.homeFragment &&
                   it.click == 0) {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
            }
        }

    }

    private fun initRecyclerView() {
        binding.rvpet.layoutManager = GridLayoutManager(requireActivity(),
            2, RecyclerView.VERTICAL, false)
    }
}