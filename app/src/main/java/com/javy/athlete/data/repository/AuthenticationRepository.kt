package com.javy.athlete.data.repository

import android.util.Log
import com.javy.athlete.BuildConfig
import com.javy.athlete.data.source.remote.rest.AthleteRemoteDataSource
import com.javy.athlete.data.source.remote.rest.AuthenticationRemoteDataSource
import com.javy.athlete.data.source.remote.rest.OauthInterceptor
import com.javy.athlete.data.source.remote.rest.TokenManager
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val dataSource: AuthenticationRemoteDataSource,
    private val athleteRemoteDataSource: AthleteRemoteDataSource,
    private val interceptor: OauthInterceptor
) {

    suspend fun token(code: String): Boolean {
        val token = dataSource.fetchToken(
            clientId = BuildConfig.CLIENT_ID,
            clientSecret = BuildConfig.CLIENT_SECRET,
            code = code
        )

        token?.access_token?.let {
            Log.d("AuthenticationRepository", "onSuccess")
            interceptor.tokenManager = TokenManager(it)
            // TODO implement a local data source that uses DataStore to store athlete data
            athleteRemoteDataSource.athlete()?.let {
                return true
            }
        }

        return false
    }
}