package com.example.mybasicview

import android.content.ContentValues
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.example.mybasicview.databinding.FragmentSecondBinding
import com.google.android.material.snackbar.Snackbar

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
    val args: SecondFragmentArgs by navArgs()
    var bandera = false
    var claveM = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(args.claveMateria != "valor_predeterminado"){
            val edtPeriodo: EditText = view.findViewById(R.id.edtPeriodo)
            val edtClaveMateria: EditText = view.findViewById(R.id.edtClaveMateria)
            val edtMateria: EditText = view.findViewById(R.id.edtMateria)
            val edtCalificacion: EditText = view.findViewById(R.id.edtCalificacion)
            val argum = args.claveMateria
            //encontar elemento en la lista del recyclcer view
            val materiaEncontrada = Singleton.kardex.find { it.claveMateria == argum }
            if (materiaEncontrada != null) {
                // Asigna los valores a los EditText
                binding.edtPeriodo.text = Editable.Factory.getInstance().newEditable(materiaEncontrada.periodo)
                edtClaveMateria.text = Editable.Factory.getInstance().newEditable(materiaEncontrada.claveMateria)
                edtMateria.text = Editable.Factory.getInstance().newEditable(materiaEncontrada.materia)
                edtCalificacion.text = Editable.Factory.getInstance().newEditable(materiaEncontrada.calificacion.toString())
            } else {
                Snackbar.make(view, "Materia no encontrada", Snackbar.LENGTH_SHORT).show()
            }
            bandera = true
            claveM = argum
        }
        // Add options menu to the toolbar
        binding.btnGuardar.setOnClickListener{
            val periodo = binding.edtPeriodo.text.toString()
            val clave_materia = binding.edtClaveMateria.text.toString()
            val materia = binding.edtMateria.text.toString()
            val calificacion = binding.edtCalificacion.text.toString().toInt()

            //val dbHelper = KardexSqliteOpenHelper(requireContext())
            //val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("clave_materia", clave_materia )
                put("materia", materia)
                put("periodo", periodo )
                put("calificacion", calificacion)
            }

            val db = Room.databaseBuilder(
                requireContext(),
                KardexDataBase::class.java, "KardexRoom"
            ).allowMainThreadQueries().build() //AllowMain.. metodo para forzar en HILO Main

            val materiaDao = db.materiaDAO()
            val materia_kardex = Materia(periodo,clave_materia,materia,calificacion)
            if(bandera){
                /*
                binding.btnGuardar.text = "Actualizar"
                val nuevosDatos = Materia(periodo, clave_materia, materia, calificacion)
                val indiceActualizar = Singleton.kardex.indexOfFirst { it.clave_materia == claveM }
                if (indiceActualizar != -1) {
                    // Actualiza el elemento en la lista
                    Singleton.kardex[indiceActualizar] = nuevosDatos
                    // Puedes también actualizar los EditText si es necesario
                    binding.edtPeriodo.text = Editable.Factory.getInstance().newEditable(nuevosDatos.periodo)
                    binding.edtClaveMateria.text = Editable.Factory.getInstance().newEditable(nuevosDatos.clave_materia)
                    binding.edtMateria.text = Editable.Factory.getInstance().newEditable(nuevosDatos.materia)
                    binding.edtCalificacion.text = Editable.Factory.getInstance().newEditable(nuevosDatos.calificacion.toString())
                } else {
                    // El elemento no se encontró, realiza alguna acción o muestra un mensaje de error
                    Snackbar.make(view, "Elemento no encontrado para actualizar", Snackbar.LENGTH_SHORT).show()
                }
                */

                //Update data
                //val selectionArgs = arrayOf(clave_materia)
                //db.update("Materia",values,"clave_materia = ?",selectionArgs)
                materiaDao.update(materia_kardex)

            }else{
                binding.btnGuardar.text = "Guardar"
                //guardar en la lista del recyclcer view


                //Singleton.kardex.add(materia_kardex)

                //Insert data

                //db?.insert("Materia", null, values)
                materiaDao.insertAll(materia_kardex)
            }

            //
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}