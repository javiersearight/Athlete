package com.javy.athlete.data.repository

import com.javy.athlete.data.source.remote.rest.AthleteRemoteDataSource
import javax.inject.Inject

class AthleteRepository @Inject constructor(
    private val dataSource: AthleteRemoteDataSource
) {

    suspend fun athlete() = dataSource.athlete()

    suspend fun athleteAndStats() = dataSource.athleteAndStats()
}