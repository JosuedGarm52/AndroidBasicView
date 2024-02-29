package com.example.mybasicview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class MateriaKardexAdapter(val xyz: (Materia) -> Unit) : RecyclerView.Adapter<MateriaKardexAdapter.ViewHolder>() {
    class ViewHolder(materia_kardex_item: View, val xyz: (Materia) -> Unit) : RecyclerView.ViewHolder(materia_kardex_item) {
        val tvMateria = materia_kardex_item.findViewById<TextView>(R.id.tvMateria)
        val tvCalificacion = materia_kardex_item.findViewById<TextView>(R.id.tvCalificacion)
        val tvPeriodo = materia_kardex_item.findViewById<TextView>(R.id.tvPeriodo)

        fun bind(materiaKardex: Materia){
            tvMateria.text = materiaKardex.materia
            tvCalificacion.text = materiaKardex.calificacion.toString()
            tvPeriodo.text = materiaKardex.periodo

            itemView.setOnClickListener{
                xyz(materiaKardex)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MateriaKardexAdapter.ViewHolder {
        val materia_kardex_item = LayoutInflater.from(parent.context).inflate(R.layout.materia_kardex_item,parent,false)
        return MateriaKardexAdapter.ViewHolder(materia_kardex_item,xyz)
    }

    override fun onBindViewHolder(
        holder: MateriaKardexAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(Singleton.kardex[position])

        //holder.tvMateria.text = Singleton.kardex[position].materia


        holder.itemView.setOnClickListener{
            xyz(Singleton.kardex[position])
        }
    }

    override fun getItemCount(): Int {
        return Singleton.kardex.size
    }
}