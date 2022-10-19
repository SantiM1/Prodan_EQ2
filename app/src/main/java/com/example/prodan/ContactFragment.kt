package com.example.prodan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.prodan.databinding.FragmentContactBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



class ContactFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    private fun openTwitter(){
        val urlPage = "https://twitter.com/prodanmty"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)))
    }
    private fun openYoutube(){
        val urlPage = "https://www.youtube.com/user/PRODANMTY"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)))
    }

    private fun openFacebook() {
        val facebookId = "fb://page/267399839949060"
        val urlPage = "https://www.facebook.com/ProdanAC"
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(facebookId)))
        } catch (e: Exception) {
            Log.e("LOGLOG", "Application not intalled.")
            //Open url web page.
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)))
        }
    }
    private fun openMail(){
        startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:informes@prodan.org.mx")))
    }
    private fun openInstagram() {
        val urlPage = "https://www.instagram.com/prodanmty/?hl=es-la"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root


        //return inflater.inflate(R.layout.fragment_contact, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.isVisible =true
        binding.btnPrivacidad.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_contactFragment_to_avisoDePrivacidadFragment)
        }


        binding.btnFacebook.setOnClickListener {
            openFacebook()
        }
        binding.btntxtFacebook.setOnClickListener {
            openFacebook()
        }
        binding.btnInstagram.setOnClickListener{
            openInstagram()
        }
        binding.btntxtInstagram.setOnClickListener {
            openInstagram()
        }
        binding.btnYoutube.setOnClickListener{
            openYoutube()
        }
        binding.btntxtYoutube.setOnClickListener {
            openYoutube()
        }
        binding.btnTwitter.setOnClickListener{
            openTwitter()
        }
        binding.btntxtTwitter.setOnClickListener {
            openTwitter()
        }
        binding.btnCorreo.setOnClickListener{
            openMail()
        }
        binding.btntxtCorreo.setOnClickListener {
            openMail()
        }
    }
}

