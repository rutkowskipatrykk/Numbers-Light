package com.example.numberslight

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.numberslight.model.Details
import com.example.numberslight.service.NumbersLightService
import com.example.numberslight.view.viewmodel.DetailsViewModel
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DetailsViewModelUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var numbersLightService: NumbersLightService

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test is init view model sets isLoading parameter for true`() {
        //arrange
        val detailsViewModel = getViewModel()

        //act
        /* no - op */

        //assert
        assertEquals(true, detailsViewModel.isLoading.value)
    }

    @Test
    fun `test service in details called correctly`() {
        //arrange
        val mockName = "1"
        val mockText = "Text"
        val mockImage = "2"
        val detailsViewModel = getViewModel()
        val details = Details(mockName, mockText, mockImage)
        `when`(numbersLightService.getDetails(mockName)).thenReturn(
            Observable.just(details)
        )

        //Act
        detailsViewModel.loadDetails(mockName)

        //assert
        verify(numbersLightService, times(1)).getDetails(mockName)
    }

    private fun getViewModel(): DetailsViewModel {
        return DetailsViewModel(numbersLightService)
    }
}