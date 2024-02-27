package com.example.mybasicview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import com.example.mybasicview.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Add options menu to the toolbar
        binding.btnGuardar.setOnClickListener{
            val periodo = binding.edtPeriodo.text.toString()
            val clave_materia = binding.edtClaveMateria.text.toString()
            val materia = binding.edtMateria.text.toString()
            val calificacion = binding.edtCalificacion.text.toString().toInt()

            val materia_kardex = Materia(periodo,clave_materia,materia,calificacion)

            Singleton.kardex.add(materia_kardex)
            //
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}