package com.example.numberslight.service

import com.example.numberslight.model.Details
import com.example.numberslight.model.ListElement
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NumbersLightService {

    @GET("test/json.php")
    fun getList(): Observable<List<ListElement>>

    @GET("test/json.php")
    fun getDetails(@Query("name") name: String): Observable<Details>

}