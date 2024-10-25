package com.javy.athlete.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.javy.athlete.ui.model.Athlete
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AthleteDataStoreLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private val ATHLETE_ID = intPreferencesKey("athlete_id")
    private val ATHLETE_FULL_NAME = stringPreferencesKey("athlete_full_name")
    private val ATHLETE_PROFILE_URL = stringPreferencesKey("athlete_profile_url")
    private val ATHLETE_FOLLOWER_COUNT = stringPreferencesKey("athlete_follower_count")
    private val ATHLETE_FRIEND_COUNT = stringPreferencesKey("athlete_friend_count")

    val athleteFlow: Flow<Athlete> =
        dataStore.data.map { preferences ->
            Athlete(
                id = preferences[ATHLETE_ID] ?: 0,
                fullName = preferences[ATHLETE_FULL_NAME] ?: "",
                profileUrl = preferences[ATHLETE_PROFILE_URL] ?: "",
                followerCount = preferences[ATHLETE_FOLLOWER_COUNT] ?: "",
                friendCount = preferences[ATHLETE_FRIEND_COUNT] ?: ""
            )
        }

    suspend fun persistAthlete(athlete: Athlete) {
        dataStore.edit { preferences ->
            preferences[ATHLETE_ID] = athlete.id
            preferences[ATHLETE_FULL_NAME] = athlete.fullName
            preferences[ATHLETE_PROFILE_URL] = athlete.profileUrl
            preferences[ATHLETE_FOLLOWER_COUNT] = athlete.followerCount
            preferences[ATHLETE_FRIEND_COUNT] = athlete.friendCount
        }
    }
}