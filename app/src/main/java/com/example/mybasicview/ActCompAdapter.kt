package com.example.mybasicview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActCompAdapter(val xyz: (ActividadComp) -> Unit) : RecyclerView.Adapter<ActCompAdapter.ViewHolder>() {
    class ViewHolder(act_comp_item : View, val xyz: (ActividadComp) -> Unit) : RecyclerView.ViewHolder(act_comp_item)  {
        val tvNombre = act_comp_item.findViewById<TextView>(R.id.tvNombre)
        val tvCreditos = act_comp_item.findViewById<TextView>(R.id.tvCreditos)

        fun bind(actividad: ActividadComp){
            tvNombre.text = actividad.nombre
            tvCreditos.text = actividad.creditos.toString()

            itemView.setOnClickListener{
                xyz(actividad)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActCompAdapter.ViewHolder {
        val actividad_complementaria_item = LayoutInflater.from(parent.context).inflate(R.layout.actividad_complementaria_item,parent,false)
        return ActCompAdapter.ViewHolder(actividad_complementaria_item,xyz)
    }

    override fun onBindViewHolder(
        holder: ActCompAdapter.ViewHolder,
        position: Int
    ) {
        holder.bind(Singleton.act_comp[position])


        holder.itemView.setOnClickListener{
            xyz(Singleton.act_comp[position])
        }
    }

    override fun getItemCount(): Int {
        return Singleton.act_comp.size
    }
}