package com.example.prodan

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prodan.data.PetX
import com.example.prodan.data.pet
import com.example.prodan.databinding.FragmentHomeBinding
import com.example.prodan.network.PetRetriever
import com.example.prodan.user.database.Favourite
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.*

class HomeFragment : Fragment() {
    //Pasar lo de API Ejemplo Aquí

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var petList: List<PetX> = emptyList()
    private var currentPetList: List<PetX> = emptyList()

    private val sortList = arrayOf("Nombre", "ID", "Edad", "Tamaño")
    private var checkedSort = 0

    private val filterList = arrayOf("Perro", "Gato", "Macho", "Hembra", "Grande", "Mediano", "Pequeño")
    private val uncheckedFilters = BooleanArray(filterList.size)
    private var checkedFilters = BooleanArray(filterList.size)
    
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
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.isVisible =true
        fetchComments()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val menuItem = menu.findItem(R.id.searchView)

        //Search bar
        val searchView: SearchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val query = newText!!.toString().lowercase()
                val filteredPets = petList.filter { it.name.lowercase().contains(query) || it.uid.lowercase().contains(query) }
                val pet = pet(filteredPets)
                renderData(pet)
                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.listSort -> showSorts()
            R.id.listFilters -> showFilters()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showSorts() {
        val prevCheckedSort = checkedSort

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Ordenar por:")
            .setNeutralButton("Cancelar") { _, _ ->
                checkedSort = prevCheckedSort
            }
            .setPositiveButton("Aplicar") { _, _ ->
                applySort(currentPetList)
            }
            .setSingleChoiceItems(sortList, checkedSort) { _, which ->
                checkedSort = which
            }
            .show()
    }

    private fun applySort(list: List<PetX>) {
        val sortedPets = when(sortList[checkedSort]) {
            "ID" -> list.sortedBy { it.uid }
            "Edad" -> list.sortedBy { it.edad }
            "Tamaño" -> list.sortedBy { it.tamaño }
            else -> list.sortedBy { it.name }
        }

        val pet = pet(sortedPets)
        renderData(pet)
    }

    private fun showFilters() {
        val prevCheckedFilters = checkedFilters

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Filtrar por:")
            .setNeutralButton("Cancelar") { _, _ ->
                checkedFilters = prevCheckedFilters
            }
            .setPositiveButton("Aplicar") { _, _ ->
                if(!checkedFilters.contentEquals(uncheckedFilters)) {
                    applyFilters()
                }
                else {
                    currentPetList = petList
                    applySort(currentPetList)
                }
            }
            .setMultiChoiceItems(filterList, checkedFilters) { _, which, checked ->
                checkedFilters[which] = checked
            }
            .show()
    }

    private fun applyFilters() {
        var filteredPets = petList


        if(checkedFilters[0] || checkedFilters[1]) {
            filteredPets = filteredPets.filter {
                (checkedFilters[0] && it.es.lowercase() == "perro") || (checkedFilters[1] && it.es.lowercase() == "gato")
            }
        }

        if(checkedFilters[2] || checkedFilters[3]) {
            filteredPets = filteredPets.filter {
                (checkedFilters[2] && it.sexo == "macho") || (checkedFilters[3] && it.sexo == "hembra")
            }
        }

        if(checkedFilters[4] || checkedFilters[5] || checkedFilters[6]) {
            filteredPets = filteredPets.filter {
                (checkedFilters[4] && it.tamaño == "grande") || (checkedFilters[5] && it.tamaño == "mediano") ||
                        (checkedFilters[6] && it.tamaño == "pequeño")
            }
        }

        currentPetList = filteredPets
        applySort(filteredPets)
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
            petResponse.pets = petResponse.pets.sortedBy { it.name }
            petList = petResponse.pets
            currentPetList = petList

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
                    evm.addFavourite(Favourite(it.name, it.fav, it.img, it.raza, it.edad, it.tamaño, it.custodia, it.id))
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