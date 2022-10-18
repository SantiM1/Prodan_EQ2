package com.example.prodan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.prodan.databinding.FragmentEditProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView




class EditProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =FragmentEditProfileBinding.inflate(inflater, container, false)
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.isVisible = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editProfile(view)


    }

    private fun editProfile(view: View){
        getValues()
        binding.saveBtn.setOnClickListener {
            val r1 = binding.ratingBar.rating
            val r2 = binding.ratingBar2.rating
            val r3 = binding.ratingBar3.rating
            val r4 = binding.ratingBar4.rating
            val r5 = binding.ratingBar5.rating
            val description = binding.descriptionEdit.text.toString()
            val preferences = this.activity?.getPreferences(Context.MODE_PRIVATE)
            if (preferences != null) {
                with(preferences.edit()){
                    putFloat("C1", r1)
                    putFloat("C2", r2)
                    putFloat("C3", r3)
                    putFloat("C4", r4)
                    putFloat("C5", r5)
                    putString("Description", description)
                    apply()
                }
            }

            Navigation.findNavController(view).navigate(R.id.action_editProfileFragment_to_userFragment2)
        }

    }

    private fun getValues(){
        val preferences = this.activity?.getPreferences(Context.MODE_PRIVATE)
        val r1 = preferences?.getFloat("C1", 0f)
        val r2 = preferences?.getFloat("C2", 0f)
        val r3 = preferences?.getFloat("C3", 0f)
        val r4 = preferences?.getFloat("C4", 0f)
        val r5 = preferences?.getFloat("C5", 0f)
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
            descriptionEdit.setText(description)

        }

    }



}