package com.javy.athlete.data.source.remote.rest.service

import com.javy.athlete.data.source.remote.rest.model.AthleteRemote
import com.javy.athlete.data.source.remote.rest.model.StatsRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AthleteApiService {
    @GET("api/v3/athlete")
    suspend fun athlete(): Response<AthleteRemote>

    @GET("api/v3/athletes/{id}/stats")
    suspend fun athleteStats(@Path("id") id: String): Response<StatsRemote>
}