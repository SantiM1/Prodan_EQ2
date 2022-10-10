package com.example.prodan

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.prodan.databinding.FragmentFormsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FormsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormsFragment : Fragment() {
    private var _binding: FragmentFormsBinding? = null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    // private var param1: String? = null
    // private var param2: String? = null

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFormsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val myWebView: WebView = view.findViewById(R.id.webviewForms)
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        val webSetting: WebSettings = myWebView.settings
        webSetting.javaScriptEnabled = true
        myWebView.webViewClient = WebViewClient()

        myWebView.canGoBack()
        myWebView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK

                && event.action == MotionEvent.ACTION_UP
                && myWebView.canGoBack()
            ) {
                myWebView.goBack()
                return@OnKeyListener true
            }
            false
        })


        myWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSc9Nj7H1W5kaIEvD2gPP2nyQ1G4iKndw90BJWHs8_Maz6tADw/viewform")
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.allowContentAccess = true
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.useWideViewPort = true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FormsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}