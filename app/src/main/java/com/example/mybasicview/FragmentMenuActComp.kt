package com.example.mybasicview

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.MenuProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mybasicview.databinding.FragmentMenuActcomBinding
import com.google.android.material.snackbar.Snackbar

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
        Log.d("FragmentMenuActComp", "OnCreateView: paso por aqui")
        _binding = FragmentMenuActcomBinding.inflate(inflater, container, false)
        return binding.root

    }
    val args: FragmentMenuActCompArgs by navArgs()
    var bandera = false
    var clave = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("FragmentMenuActComp", "OnViewCreate: paso por aqui")

        if (args.nombre != "nulo") {
            //encontar elemento en la lista del recyclcer view
            val materiaEncontrada = Singleton.act_comp.find { it.nombre == args.nombre }
            if (materiaEncontrada != null) {
                // Asigna los valores a los EditText
                binding.edtNombre.setText(materiaEncontrada.nombre)
                binding.edtCreditos.text =
                    Editable.Factory.getInstance().newEditable(materiaEncontrada.creditos.toString())
            } else {
                Snackbar.make(view, "Act_Comp no encontrada", Snackbar.LENGTH_SHORT).show()
            }
            bandera = true
            clave = args.nombre
            if(bandera){
                binding.btnSubir.text = "Actualizar"
                binding.btnEliminar.visibility = View.VISIBLE
            }else{
                binding.btnSubir.text = "Guardar"
                binding.btnEliminar.visibility = View.GONE
            }
        }
        binding.textView2.setOnClickListener {
            Snackbar.make(view, "Eyy, esto es solo un letrero", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        binding.btnSubir.setOnClickListener {
            val nombre = binding.edtNombre.text.toString()
            val creditos = binding.edtCreditos.text.toString().toInt()
            if (bandera) {
                val nuevosDatos = ActividadComp(nombre, creditos)
                val indiceActualizar = Singleton.act_comp.indexOfFirst { it.nombre == clave }
                if (indiceActualizar != -1) {
                    // Actualiza el elemento en la lista
                    Singleton.act_comp[indiceActualizar] = nuevosDatos

                    binding.edtNombre.text =
                        Editable.Factory.getInstance().newEditable(nuevosDatos.nombre)
                    binding.edtCreditos.text =
                        Editable.Factory.getInstance().newEditable("" + nuevosDatos.creditos)
                } else {
                    // El elemento no se encontró, realiza alguna acción o muestra un mensaje de error
                    Snackbar.make(
                        view,
                        "Elemento no encontrado para actualizar",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                //guardar en la lista del recyclcer view
                val act_comp = ActividadComp(nombre, creditos)

                Singleton.act_comp.add(act_comp)
            }
            findNavController().navigate(R.id.action_FragmentFormActComp_to_FragmentRecyclerActComp)
        }
        binding.btnEliminar.setOnClickListener{
            val actCompEncontrada = Singleton.act_comp.find { it.nombre == args.nombre }

            if (actCompEncontrada != null) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Confirmación")
                builder.setMessage("¿Estás seguro de que deseas eliminar ${actCompEncontrada.nombre}?")

                // Configurar el botón de confirmación
                builder.setPositiveButton("Sí") { _, _ ->
                    // Remover el elemento encontrado de la lista
                    Singleton.act_comp.remove(actCompEncontrada)
                    // Realizar otras operaciones si es necesario
                    Snackbar.make(view, "Act. Comp. ${args.nombre} eliminada", Snackbar.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_FragmentFormActComp_to_FragmentRecyclerActComp)
                }

                // Configurar el botón de cancelación
                builder.setNegativeButton("No") { _, _ ->
                    Snackbar.make(view, "Eliminación cancelada", Snackbar.LENGTH_SHORT).show()
                }

                // Mostrar el cuadro de diálogo
                builder.show()
            } else {
                Snackbar.make(view, "Act_Comp no encontrada", Snackbar.LENGTH_SHORT).show()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}