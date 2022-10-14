package com.example.prodan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prodan.data.pet
import com.example.prodan.databinding.FragmentHomeBinding
import com.example.prodan.network.PetRetriever
import com.example.prodan.user.database.Favourite
import kotlinx.coroutines.*

class HomeFragment : Fragment() {
    //Pasar lo de API Ejemplo Aquí

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val evm : FavouriteViewModel by viewModels {
        FavouriteViewModelFactory((activity?.application as ProdanApp).repositoryFavourite)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    private fun fetchComments() {
        val petsFetchJob = Job()

        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Toast.makeText(requireContext() , "Error" , Toast.LENGTH_SHORT).show()
        }

        val scope = CoroutineScope(petsFetchJob + Dispatchers.Main)
        scope.launch(errorHandler){

            //fetch data
            val petResponse = petRetriever.getPets()

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
        }



    }

    private fun initRecyclerView() {
        binding.rvpet.layoutManager = GridLayoutManager(requireActivity(),
            2, RecyclerView.VERTICAL, false)
    }
}