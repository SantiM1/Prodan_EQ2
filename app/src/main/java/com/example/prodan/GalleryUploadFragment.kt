package com.example.prodan


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.prodan.databinding.FragmentGalleryUploadBinding
import com.example.prodan.user.database.GalleryImg
import com.google.android.material.bottomnavigation.BottomNavigationView


class GalleryUploadFragment : Fragment() {

    private var _binding: FragmentGalleryUploadBinding ? = null
    private val binding get() = _binding!!
    private var imageUri : Uri? =null



    private val evm : GalleryImgViewModel by viewModels {
        GalleryImgViewModelFactory((activity?.application as ProdanApp).repositoryGallery)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryUploadBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.isVisible =false

        binding.FileBtn.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK)
            galleryIntent.type = "image/*"
            imagePickerActivityResult.launch(galleryIntent)
        }



        binding.UploadBtn.setOnClickListener{
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()
            val id = "Photo" + title.hashCode()+description.hashCode()
            uploadToCloudinary(imageUri, id)

            val galleryImg = GalleryImg(title, description, id)

            evm.addGalleryImg(galleryImg)

            Navigation.findNavController(view).navigate(R.id.action_galleryUploadFragment_to_userFragment)
        }

    }


    var imagePickerActivityResult: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result != null) {
            imageUri= result.data?.data
            //uploadToCloudinary(imageUri)
            Glide.with(requireActivity())
                .load(imageUri)
                .into(binding.imgPreview)
        }
    }

    private fun uploadToCloudinary(filepath: Uri?, publicId: String){
        MediaManager.get().upload(filepath).unsigned("hzvz9qtf").option("public_id", publicId).callback(object : UploadCallback {
            override fun onSuccess(requestId: String?, resultData: MutableMap<Any?, Any?>?) {
                //Toast.makeText(requireContext(), "Task successful", Toast.LENGTH_SHORT).show()
            }

            override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                //Toast.makeText(requireContext(), "T", Toast.LENGTH_SHORT).show()
            }

            override fun onReschedule(requestId: String?, error: ErrorInfo?) {
                //Toast.makeText(requireContext(), "Task Not reschedule"+ error, Toast.LENGTH_SHORT).show()
            }

            override fun onError(requestId: String?, error: ErrorInfo?) {

                //Toast.makeText(requireContext(), "Task Not successful"+ error, Toast.LENGTH_SHORT).show()
            }

            override fun onStart(requestId: String?) {

                //Toast.makeText(requireContext(), "Start", Toast.LENGTH_SHORT).show()
            }
        }).dispatch()

    }
}