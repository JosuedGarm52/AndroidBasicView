package com.example.mybasicview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mybasicview.databinding.FragmentFirstBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }
    //agg
    private val viewModel: KardexViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

        Singleton.kardex.clear()
        //val db = KardexSqliteOpenHelper(requireContext())
        //db.getAllKardexItems()
        val db = Room.databaseBuilder(
            requireContext(),
            KardexDataBase::class.java, "KardexRoom"
        ).allowMainThreadQueries().build()

        val MateriaDAO = db.materiaDAO()

        Singleton.kardex.addAll(MateriaDAO.getAll())

        /*
        val adapter = MateriaKardexAdapter{
            onItemClick(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        */

        // Recolecta el estado del viewModel utilizando viewLifecycleOwner.lifecycleScope
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Crear el adaptador dentro del ciclo de vida del fragmento
                val adapter = MateriaKardexAdapter { materia ->
                    onItemClick(materia)
                }

                // Asignar el adaptador al RecyclerView
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

                // Recolecta el estado del viewModel utilizando viewLifecycleOwner.lifecycleScope
                viewModel.uiState.collect { materia ->
                    val materiaList = listOf(materia)
                    adapter.submitList(materiaList)
                }
            }
        }
    }

    private fun onItemClick(it: Materia) {
        Log.d("FirstFragment", "onItem clic")
        Toast.makeText(requireContext(), "Clic a ${it.materia}", Toast.LENGTH_SHORT).show()

        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(it.claveMateria)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}