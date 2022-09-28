package com.example.prodan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prodan.databinding.FragmentUserBinding
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.utils.ColorTemplate

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

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

    fun setVarChart() {

        val entries: MutableList<RadarEntry> = ArrayList()
        entries.add(RadarEntry(0f, 30f))
        entries.add(RadarEntry(1f, 80f))
        entries.add(RadarEntry(2f, 60f))
        entries.add(RadarEntry(3f, 50f))
        entries.add(RadarEntry(5f, 70f))
        entries.add(RadarEntry(6f, 60f))

        val set = RadarDataSet(entries, "BarDataSet")
        var colorList = mutableListOf<Int>()
        ColorTemplate.JOYFUL_COLORS.forEach {colorList.add(it)  }



        // var colorList = mutableListOf<Int>(ColorTemplate.JOYFUL_COLORS.forEach {  })
        set.setColors(colorList)
        // set.setColors(ColorTemplate.COLORFUL_COLORS)

        var data = RadarData(set)
        binding.chart1.data = data
        binding.chart1.animateY(2000)
        //   binding.migrafica.invalidate()


    }

}