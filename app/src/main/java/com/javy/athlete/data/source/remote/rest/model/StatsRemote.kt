package com.javy.athlete.data.source.remote.rest.model

class StatsRemote {
    val recent_run_totals: TotalStatItemRemote? = null
    val all_run_totals: TotalStatItemRemote? = null
    val recent_swim_totals: TotalStatItemRemote? = null
    val all_swim_totals: TotalStatItemRemote? = null
    val ytd_run_totals: TotalStatItemRemote? = null
}

class TotalStatItemRemote {
    val count: Int? = null
    val distance: Double? = null
}