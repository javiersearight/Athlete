package com.javy.athlete.data.source.remote.rest.service

import com.javy.athlete.data.source.remote.rest.model.AthleteRemote
import com.javy.athlete.data.source.remote.rest.model.Token
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OauthApiService {

    @GET("api/v3/athlete")
    suspend fun athlete(): Response<AthleteRemote>

    @POST("oauth/token")
    suspend fun requestToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("grant_type") grantType: String = "authorization_code"
    ): Response<Token>
}