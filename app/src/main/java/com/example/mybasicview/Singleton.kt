package com.example.mybasicview

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

object Singleton {
    val kardex = mutableListOf<Materia>()
    val act_comp = mutableListOf<ActividadComp>()
    val kardexFlow: MutableStateFlow<List<Materia>> = MutableStateFlow(emptyList())
    // En cualquier momento que actualices kardex, también actualiza kardexFlow
    fun updateKardex(newKardex: List<Materia>) {
        kardex.clear()
        kardex.addAll(newKardex)
        kardexFlow.value = kardex.toList() // actualiza el valor de kardexFlow
    }


}