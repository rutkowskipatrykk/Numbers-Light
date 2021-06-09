package com.example.numberslight.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numberslight.model.ListElement
import com.example.numberslight.service.NumbersLightService
import com.example.numberslight.view.Event
import com.example.numberslight.view.recyclerview.ListAdapter
import com.example.numberslight.view.recyclerview.OnClickListElement
import io.reactivex.schedulers.Schedulers

class ListViewModel(
    private val numbersLightService: NumbersLightService
) : ViewModel() {

    val onClickItem = MutableLiveData<Event<ListElement>>()

    val recyclerViewAdapter = ListAdapter(
        arrayListOf(),
        object : OnClickListElement {
            override fun onClick(item: ListElement) {
                onClickItem.postValue(Event(item))
            }
        },
        null
    )

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = true
    }

    fun loadList(selectedItem: ListElement?) {
        _isLoading.value = true
        numbersLightService.getList()
            .subscribeOn(Schedulers.io())
            .blockingSubscribe(
                {
                    it?.let {
                        recyclerViewAdapter.setItemList(it)
                        recyclerViewAdapter.setSelectedItem(selectedItem)
                        _isLoading.postValue(false)
                    }
                },
                {
                    Log.e("CALL_ERROR", "Call error: ${it.message}")
                }
            )

        _isLoading.postValue(false)
    }
}
