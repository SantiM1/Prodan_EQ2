package com.example.prodan

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.prodan.databinding.FragmentUserBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
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
        binding.nextBtn.visibility = View.GONE

        return binding.root


    }

    fun setVarChart() {

        val xvalues = ArrayList<String>()
        xvalues.apply{
            add("Bueno con niños")
            add("Sociable")
            add("Energético")
            add("Amigable")
            add("Travieso")
        }



        val entries: MutableList<RadarEntry> = ArrayList()
        entries.add(RadarEntry(1f, "Bueno con niños"))
        entries.add(RadarEntry(2f, "Sociable"))
        entries.add(RadarEntry(3f, "Energético"))
        entries.add(RadarEntry(4f, "Amigable"))
        entries.add(RadarEntry(5f, "Travieso"))

        val set = RadarDataSet(entries, "RadarDataSet")
        var colorList = mutableListOf<Int>()
        ColorTemplate.JOYFUL_COLORS.forEach {colorList.add(it)  }



        // var colorList = mutableListOf<Int>(ColorTemplate.JOYFUL_COLORS.forEach {  })
        set.color = Color.rgb(10, 110, 180)
        set.fillColor = Color.rgb(10, 110, 180);
        set.setDrawFilled(true);
        set.fillAlpha = 180;
        set.lineWidth = 2f;
        set.isDrawHighlightCircleEnabled = true;
        set.setDrawHighlightIndicators(false);
        // set.setColors(ColorTemplate.COLORFUL_COLORS)

        var data = RadarData(set)
        binding.radarChart.data = data
        binding.radarChart.animateY(2000)
        binding.radarChart.description.isEnabled = false
        binding.radarChart.legend.isEnabled = false
        binding.radarChart.description.text = "Datos"
        binding.radarChart.xAxis.valueFormatter = (myValueFormater(xvalues))

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