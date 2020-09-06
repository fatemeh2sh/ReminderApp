package com.example.appreminder.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appreminder.data.model.Reminder
import com.example.appreminder.data.repository.MainRepository
import com.example.appreminder.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository):ViewModel() {

    private val _vReminder = MutableLiveData<Resource<List<Reminder>>>()
    val vReminder:LiveData<Resource<List<Reminder>>>
        get() = _vReminder

    init {
        getReminder()
    }

    private fun getReminder(){
        viewModelScope.launch {
            mainRepository.getAll().collect {
                _vReminder.value = it
            }
        }
    }

    fun delete(id:Int){
        viewModelScope.launch {
            mainRepository.delete(id)
        }
    }

}