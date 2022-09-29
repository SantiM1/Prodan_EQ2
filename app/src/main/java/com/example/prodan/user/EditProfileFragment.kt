package com.example.prodan.user

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prodan.databinding.FragmentEditProfileBinding
import android.widget.RatingBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.Description

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =FragmentEditProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editProfile()

    }

    private fun editProfile(){
        getValues()
        /*
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            val r1 = fl
            Toast.makeText(this.requireActivity(),  fl.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.ratingBar2.setOnRatingBarChangeListener { ratingBar, fl, b ->
            val r2 = fl
            Toast.makeText(this.requireActivity(),  fl.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.ratingBar3.setOnRatingBarChangeListener { ratingBar, fl, b ->
            val r3 = fl
            Toast.makeText(this.requireActivity(),  fl.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.ratingBar4.setOnRatingBarChangeListener { ratingBar, fl, b ->
            val r4 = fl
            Toast.makeText(this.requireActivity(),  fl.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.ratingBar5.setOnRatingBarChangeListener { ratingBar, fl, b ->
            val r5 = fl
            Toast.makeText(this.requireActivity(), fl.toString(), Toast.LENGTH_SHORT).show()
        }
        binding.PetNameEdit.setOnFocusChangeListener { v, hasFocus  ->
            if(!hasFocus) {
                Toast.makeText(this.requireActivity(),  binding.PetNameEdit.text, Toast.LENGTH_LONG).show()
            }
        }
        binding.descriptionEdit.setOnFocusChangeListener { v, hasFocus  ->
            if(!hasFocus) {
                Toast.makeText(this.requireActivity(),  binding.descriptionEdit.text, Toast.LENGTH_LONG).show()
            }
        }
        */
        binding.saveBtn.setOnClickListener {
            val r1 = binding.ratingBar.rating
            val r2 = binding.ratingBar2.rating
            val r3 = binding.ratingBar3.rating
            val r4 = binding.ratingBar4.rating
            val r5 = binding.ratingBar5.rating
            val name =binding.PetNameEdit.text.toString()
            val description = binding.descriptionEdit.text.toString()
            val preferences = this.activity?.getPreferences(Context.MODE_PRIVATE)
            if (preferences != null) {
                with(preferences.edit()){
                    putFloat("C1", r1)
                    putFloat("C2", r2)
                    putFloat("C3", r3)
                    putFloat("C4", r4)
                    putFloat("C5", r5)
                    putString("PetName", name)
                    putString("Description", description)
                    apply()
                }
            }
        }
    }

    fun getValues(){
        val preferences = this.activity?.getPreferences(Context.MODE_PRIVATE)
        val r1 = preferences?.getFloat("C1", 0f)
        val r2 = preferences?.getFloat("C2", 0f)
        val r3 = preferences?.getFloat("C3", 0f)
        val r4 = preferences?.getFloat("C4", 0f)
        val r5 = preferences?.getFloat("C5", 0f)
        val name =preferences?.getString("PetName", "")
        val description = preferences?.getString("Description", "")
        binding.apply {
            if (r1 != null) {
                ratingBar.rating = r1
            }
            if (r2 != null) {
                ratingBar2.rating = r2
            }
            if (r3 != null) {
                ratingBar3.rating  = r3
            }
            if (r4 != null) {
                ratingBar4.rating = r4
            }
            if (r5 != null) {
                ratingBar5.rating = r5
            }
            PetNameEdit.setText(name)
            descriptionEdit.setText(description)

        }

    }



}