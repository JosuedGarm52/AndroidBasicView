package com.example.mybasicview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData

class FirstFragmentViewModel(private val repository: KardexRepository) : ViewModel(){
    val materiasKardex : LiveData<List<Materia>> = repository.allMateriasKardex.asLiveData()
}
class FirstFragmentViewModelFactory(private val repository: KardexRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FirstFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}