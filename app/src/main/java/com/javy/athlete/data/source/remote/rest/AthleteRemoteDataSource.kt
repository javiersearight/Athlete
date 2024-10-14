package com.javy.athlete.data.source.remote.rest

import com.javy.athlete.data.source.remote.rest.model.AthleteRemote
import com.javy.athlete.data.source.remote.rest.model.StatsRemote
import com.javy.athlete.data.source.remote.rest.model.TotalStatItemRemote
import com.javy.athlete.data.source.remote.rest.service.AthleteApiService
import com.javy.athlete.ui.model.Athlete
import com.javy.athlete.ui.model.Stats
import com.javy.athlete.ui.model.TotalStatsItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AthleteRemoteDataSource @Inject constructor(private val apiService: AthleteApiService) {

    suspend fun athlete(): AthleteRemote? {
        val response = apiService.athlete()

        if (response.code() == 401) {
            return null
        }
        return response.body()
    }

    suspend fun athleteAndStats(): Flow<Athlete> {
        return flow {
            val athleteResponse = apiService.athlete()
            val athleteRemote = athleteResponse.body()
            athleteRemote?.let {
                val athlete = it.athlete()
                emit(athlete)

                val statsResponse = apiService.athleteStats(athlete.id.toString())
                val statsRemote = statsResponse.body()
                statsRemote?.let { nullSafeStats ->
                    val athleteAndStats = athlete.copy(stats = nullSafeStats.stats())
                    emit(athleteAndStats)
                }
            }
        }
    }

    private fun AthleteRemote.athlete(): Athlete =
        Athlete(
            id = id ?: 0,
            username = username ?: "",
            fullName = "$firstname $lastname",
            profileUrl = profile ?: "",
            followerCount = follower?.let { "$it" } ?: "0",
            friendCount = friend?.let { "$it" } ?: "0"
        )

    private fun StatsRemote.stats(): Stats =
        Stats(
            recentRunTotals = recent_run_totals.totalStats(),
            allRunTotals = all_run_totals.totalStats(),
            recentSwimTotals = recent_swim_totals.totalStats(),
            allSwimTotals = all_swim_totals.totalStats(),
            ytdRunTotals = ytd_run_totals.totalStats()
        )

    private fun TotalStatItemRemote?.totalStats(): TotalStatsItem =
        if (this != null) {
            TotalStatsItem(
                count = count?.let { "$it" } ?: "0",
                distance = distance?.let { "$it m" } ?: "0 m"
            )
        } else TotalStatsItem()
}