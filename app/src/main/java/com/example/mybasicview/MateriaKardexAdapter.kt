package com.example.mybasicview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class MateriaKardexAdapter() : RecyclerView.Adapter<MateriaKardexAdapter.ViewHolder>() {
    class ViewHolder(materia_kardex_item: View) : RecyclerView.ViewHolder(materia_kardex_item) {
        val tvMateria = materia_kardex_item.findViewById<TextView>(R.id.tvMateria)
        val tvCalificacion = materia_kardex_item.findViewById<TextView>(R.id.tvCalificacion)
        val tvPeriodo = materia_kardex_item.findViewById<TextView>(R.id.tvPeriodo)

        fun bind(materiaKardex: Materia){
            tvMateria.text = materiaKardex.materia
            tvCalificacion.text = materiaKardex.calificacion.toString()
            tvPeriodo.text = materiaKardex.periodo
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MateriaKardexAdapter.ViewHolder {
        val materia_kardex_item = LayoutInflater.from(parent.context).inflate(R.layout.materia_kardex_item,parent,false)
        return MateriaKardexAdapter.ViewHolder(materia_kardex_item)
    }

    override fun onBindViewHolder(
        holder: MateriaKardexAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(Singleton.kardex[position])

        //holder.tvMateria.text = Singleton.kardex[position].materia

        //si da en holder.tvMateria.setOnClickListener para solo ese elemento
        //para cualquier parte del elemento es con itemView
        holder.itemView.setOnClickListener { v ->
            Toast.makeText(holder.itemView.context, "Clic a ${Singleton.kardex[position].materia}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return Singleton.kardex.size
    }
}