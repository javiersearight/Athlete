package com.javy.athlete.ui.model

data class Athlete(
    val id: Int = 0,
    val username: String = "",
    val fullName: String = "",
    val profileUrl: String = "",
    val followerCount: String = "0",
    val friendCount: String = "0",
    val stats: Stats? = null
)
