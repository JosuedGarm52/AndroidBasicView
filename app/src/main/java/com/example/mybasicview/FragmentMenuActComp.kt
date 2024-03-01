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
import com.example.mybasicview.databinding.FragmentMenuActcomBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FragmentMenuActComp : Fragment() {

    private var _binding: FragmentMenuActcomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuActcomBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Add options menu to the toolbar
        binding.btnSubir.setOnClickListener{
            val nombre = binding.edtNombre.text.toString()
            val creditos = binding.edtCreditos.text.toString().toInt()

            val Actividad = ActividadComp(nombre,creditos)

            Singleton.act_comp.add(Actividad)
            //
            findNavController().navigate(R.id.action_FragmentFormActComp_to_FragmentRecyclerActComp)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}