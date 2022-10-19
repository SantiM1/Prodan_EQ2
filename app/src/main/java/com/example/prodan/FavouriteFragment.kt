package com.example.prodan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prodan.data.PetX
import com.example.prodan.data.pet
import com.example.prodan.databinding.FragmentFavouritesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {


    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val evm : FavouriteViewModel by viewModels {
        FavouriteViewModelFactory((activity?.application as ProdanApp).repositoryFavourite)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        evm.getAllFavourites().observe((viewLifecycleOwner)) {
            val adapter = FavouriteAdapter(it) {

                val bundle = Bundle()
                bundle.putParcelable("favourite", it)
                val petbundle = Bundle()
                petbundle.putParcelable("pet", PetX("", it.custodia, "", "", it.edad,"","", it.pid ,it.imgUrl, it.name, it.raza,"",it.size, "", "",  it.fav, it.pid))
                Navigation.findNavController(view).navigate(R.id.action_favoritesFragment_to_detailsFragment, petbundle)
            }

            binding.rvpet.adapter = adapter
            binding.rvpet.layoutManager = GridLayoutManager(requireActivity(),
                2, RecyclerView.VERTICAL, false)
        }



         
    }

}