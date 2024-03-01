package com.example.mybasicview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybasicview.databinding.FragmentRecyclerActcomBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FragmentRecyclerActComp : Fragment() {

    private var _binding: FragmentRecyclerActcomBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecyclerActcomBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

        val adapter = ActCompAdapter{
            onItemClick(it)
        }

        binding.RecyclerView2.adapter = adapter
        binding.RecyclerView2.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onItemClick(it: ActividadComp) {
        Toast.makeText(requireContext(), "Clic a ${it.nombre}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}