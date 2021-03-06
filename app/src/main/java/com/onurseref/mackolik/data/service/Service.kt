package com.onurseref.mackolik.data.service

import com.onurseref.mackolik.common.models.NewsResponse
import com.onurseref.mackolik.common.models.ScoresResponse
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

interface Service {

    @GET("latestnews")
    fun getNews(): Observable<NewsResponse>


    @GET("scores")
    fun getScores(): Observable<ScoresResponse>

}
