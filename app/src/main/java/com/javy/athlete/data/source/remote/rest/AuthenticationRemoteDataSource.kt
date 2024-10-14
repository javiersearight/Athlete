package com.javy.athlete.data.source.remote.rest

import com.javy.athlete.data.source.remote.rest.model.Token
import com.javy.athlete.data.source.remote.rest.service.OauthApiService
import javax.inject.Inject

class AuthenticationRemoteDataSource @Inject constructor(
    private val apiService: OauthApiService
) {

    suspend fun fetchToken(clientId: String, clientSecret: String, code: String): Token? {
        val response = apiService.requestToken(clientId, clientSecret, code)
        return response.body()
    }
}