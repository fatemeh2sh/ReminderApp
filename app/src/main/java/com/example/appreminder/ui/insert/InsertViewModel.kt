package com.example.appreminder.ui.insert

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appreminder.data.model.Reminder
import com.example.appreminder.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertViewModel @ViewModelInject constructor
    (private var mainRepository: MainRepository) :
    ViewModel() {

    fun insert(item:Reminder){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.insertReminder(item)
        }
    }
}