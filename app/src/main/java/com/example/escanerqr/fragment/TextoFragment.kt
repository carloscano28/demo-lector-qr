package com.example.escanerqr.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.escanerqr.databinding.FragmentTextoBinding


//RECIBE PARAMETROS
private const val TEXTO_PARAM = "param1"

class TextoFragment : Fragment() {

    private var _binding: FragmentTextoBinding? = null
    private val binding get() = _binding!!

    private var miTexto: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            miTexto = it.getString(TEXTO_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTextoBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(texto: String) =
            TextoFragment().apply {
                arguments = Bundle().apply {
                    putString(TEXTO_PARAM, texto)

                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtTexto.text= miTexto

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}