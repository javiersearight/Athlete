package com.javy.athlete.data.repository

import com.javy.athlete.data.source.local.AthleteDataStoreLocalDataSource
import com.javy.athlete.data.source.remote.rest.AthleteRemoteDataSource
import com.javy.athlete.ui.model.Athlete
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AthleteRepository @Inject constructor(
    private val athleteRemoteDataSource: AthleteRemoteDataSource,
    private val athleteDataStoreLocalDataSource: AthleteDataStoreLocalDataSource
) {

    suspend fun athlete(): Athlete? {
        val athlete = athleteRemoteDataSource.athlete()
        athlete?.let {
            athleteDataStoreLocalDataSource.persistAthlete(it)
        }
        return athlete
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun athleteAndStats(): Flow<Athlete> {
        val localFlow = athleteDataStoreLocalDataSource.athleteFlow
        val remoteFlow = athleteRemoteDataSource.athleteAndStats()
        return flowOf(localFlow, remoteFlow).flattenConcat()
    }
}