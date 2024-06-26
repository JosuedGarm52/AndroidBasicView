package com.example.mybasicview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


class MateriaKardexAdapter(private val xyz: (Materia) -> Unit) : ListAdapter<Materia, MateriaKardexAdapter.ViewHolder>(MateriaComparator()){
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
        val materia_kardex = getItem(position)
        holder.bind(materia_kardex)


    }
    class MateriaComparator : DiffUtil.ItemCallback<Materia>() {
        override fun areItemsTheSame(oldItem: Materia, newItem: Materia): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Materia, newItem: Materia): Boolean {
            return oldItem.claveMateria == newItem.claveMateria
        }
    }
}