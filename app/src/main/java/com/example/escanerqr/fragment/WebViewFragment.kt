package com.example.escanerqr.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.escanerqr.R
import com.example.escanerqr.databinding.FragmentWebViewBinding

private const val URL_PARAM = "param1"

class WebViewFragment: Fragment() {

    private var _binding:FragmentWebViewBinding ?= null
    private val binding get() = _binding!!

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(URL_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        val view = binding.root

        //Toast.makeText(   activity,   "Es url en el fragment: $url" , Toast.LENGTH_LONG  ).show()

        binding.myWebView.webViewClient = WebViewClient()
        binding.myWebView.settings.javaScriptEnabled= true
        binding.myWebView.loadUrl(url!!)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //binding.myWebView.webViewClient = WebViewClient()
        //binding.myWebView.settings.javaScriptEnabled= true
        //binding.myWebView.loadUrl(url!!)

    }

    companion object {

        @JvmStatic
        fun newInstance(url: String) =
            TextoFragment().apply {
                arguments = Bundle().apply {
                    putString(URL_PARAM, url)

                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}



