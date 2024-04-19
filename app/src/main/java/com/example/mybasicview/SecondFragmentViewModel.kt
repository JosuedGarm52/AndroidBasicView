package com.example.mybasicview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class SecondFragmentViewModel (private val repository: KardexRepository): ViewModel() {

    /*
    private val _registroGuardado = MutableLiveData(false)
    val registroGuardado: LiveData<Boolean>
        get(){
            return _registroGuardado
        }
    */

    fun insertMateria(materia: Materia) = viewModelScope.launch{
        //_registroGuardado.value = true

        repository.insert(materia)
    }
}

class SecondFragmentViewModelFactory(private val repository: KardexRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SecondFragmentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SecondFragmentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}