package com.example.numberslight.di.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.numberslight.service.NumbersLightService
import com.example.numberslight.view.viewmodel.DetailsViewModel
import com.example.numberslight.view.viewmodel.ListViewModel
import javax.inject.Inject

class MyViewModelFactory @Inject constructor(
    private val service: NumbersLightService
) : ViewModelAssistedFactory<ListViewModel> {
    override fun create(handle: SavedStateHandle): ListViewModel {
        return ListViewModel(service)
    }
}

class DetailsViewModelFactory @Inject constructor(
    private val service: NumbersLightService
) : ViewModelAssistedFactory<DetailsViewModel> {
    override fun create(handle: SavedStateHandle): DetailsViewModel {
        return DetailsViewModel(service)
    }
}