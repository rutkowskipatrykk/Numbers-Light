package com.example.numberslight.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numberslight.model.ListElement
import com.example.numberslight.view.Event

class MainActivityViewModel : ViewModel() {

    val selectedItem = MutableLiveData<Event<ListElement>>()

    private val _isNetworkError = MutableLiveData(false)
    val isNetworkError: LiveData<Boolean>
        get() = _isNetworkError

    fun setErrorStatus(isNetworkAvailable: Boolean) {
        _isNetworkError.value = !isNetworkAvailable
    }

}