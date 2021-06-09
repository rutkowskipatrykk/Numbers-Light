package com.example.numberslight.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numberslight.service.NumbersLightService
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(
    private val service: NumbersLightService
) : ViewModel() {

    private var selectedId: String? = null

    private val _text: MutableLiveData<String> = MutableLiveData()
    val text: LiveData<String>
        get() = _text

    private val _image: MutableLiveData<String> = MutableLiveData()
    val image: LiveData<String>
        get() = _image

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
    }

    fun loadDetails(id: String? = selectedId) {
        id?.let {
            selectedId = id
            service.getDetails(id)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        it?.let {
                            _text.postValue(it.text)
                            _image.postValue(it.image)
                            _isLoading.postValue(false)
                        }
                    },
                    {
                        Log.e("CALL_ERROR", "Call error: ${it.message}")
                    }
                )
        }
    }
}