package com.example.prodan

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.cloudinary.android.MediaManager
import com.example.prodan.databinding.FragmentUserBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.bottomnavigation.BottomNavigationView


class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private val evm : GalleryImgViewModel by viewModels {
        GalleryImgViewModelFactory((activity?.application as ProdanApp).repositoryGallery)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =FragmentUserBinding.inflate(inflater, container, false)
        setVarChart()

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar.isVisible =true
        binding.nextBtn.visibility = View.GONE
        binding.ProfilePicture.setOnClickListener{
            var dialog = RegisterFragment()
            parentFragmentManager.let { it1 -> dialog.show(it1, "customDialog") }
            dialog.isCancelable = true
        }
        binding.editBtn.setOnClickListener {
            //   findNavController().navigate(R.id.action_userFragment_to_editProfileFragment)
            Navigation.findNavController(view)
                .navigate(R.id.action_userFragment_to_editProfileFragment)

        }
        binding.galleryBtn.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_userFragment_to_galleryUploadFragment)

            }
        evm.getAllGalleryImgs().observe((viewLifecycleOwner)) {
            val adapter = GalleryImgAdapter(it) {

                val bundle = Bundle()
                bundle.putParcelable("galleryImg", it)
                //Navigation.findNavController(view)
                    //.navigate(R.id.action_userFragment_to_galleryUploadFragment)
            }

            binding.rvPetImages.adapter = adapter
            binding.rvPetImages.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
        }

    private fun setVarChart() {

        val preferences = this.activity?.getPreferences(Context.MODE_PRIVATE)
        val r1 = preferences?.getFloat("C1", 0f)
        val r2 = preferences?.getFloat("C2", 0f)
        val r3 = preferences?.getFloat("C3", 0f)
        val r4 = preferences?.getFloat("C4", 0f)
        val r5 = preferences?.getFloat("C5", 0f)
        val description = preferences?.getString("Description", "")

        if(description != ""){

            binding.PetDescription.text = description
        }



        val xvalues = ArrayList<String>()
        xvalues.apply{
            add("Bueno con niños")
            add("Sociable")
            add("Energético")
            add("Amigable")
            add("Travieso")
        }



        val entries: MutableList<RadarEntry> = ArrayList()
        r1?.let { RadarEntry(it, "Bueno con niños") }?.let { entries.add(it) }
        r2?.let { RadarEntry(it, "Sociable") }?.let { entries.add(it) }
        r3?.let { RadarEntry(it, "Energético") }?.let { entries.add(it) }
        r4?.let { RadarEntry(it, "Amigable") }?.let { entries.add(it) }
        r5?.let { RadarEntry(it, "Travieso") }?.let { entries.add(it) }

        val set = RadarDataSet(entries, "RadarDataSet")
        var colorList = mutableListOf<Int>()
        ColorTemplate.JOYFUL_COLORS.forEach {colorList.add(it)  }



        // var colorList = mutableListOf<Int>(ColorTemplate.JOYFUL_COLORS.forEach {  })
        set.color = Color.rgb(10, 120, 180)
        set.fillColor = Color.rgb(52, 173, 207);
        set.setDrawFilled(true);
        set.fillAlpha = 180;
        set.lineWidth = 2f;
        set.isDrawHighlightCircleEnabled = true;
        set.setDrawHighlightIndicators(false);
        // set.setColors(ColorTemplate.COLORFUL_COLORS)

        var data = RadarData(set)
        binding.apply {
            radarChart.data = data
            radarChart.animateY(2000)
            radarChart.description.isEnabled = false
            radarChart.legend.isEnabled = false
            radarChart.description.text = "Datos"
            radarChart.xAxis.valueFormatter = (myValueFormater(xvalues))
            radarChart.yAxis.axisMinimum = 0f
            radarChart.yAxis.axisMaximum = 5f
            radarChart.yAxis.isEnabled = false
        }

            //   binding.migrafica.invalidate()



    }

}
class myValueFormater(private val xvalues : ArrayList<String>) : ValueFormatter(){
    override fun getFormattedValue(value: Float): String {
        return value.toString()
    }

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        if (value.toInt() >= 0 && value.toInt() <= xvalues.size - 1) {
            return xvalues[value.toInt()]
        } else {
            return ("").toString()
        }
    }

}

