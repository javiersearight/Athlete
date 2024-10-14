package com.javy.athlete.ui.model

data class Stats(
    val recentRunTotals: TotalStatsItem = TotalStatsItem(),
    val allRunTotals: TotalStatsItem = TotalStatsItem(),
    val recentSwimTotals: TotalStatsItem = TotalStatsItem(),
    val allSwimTotals: TotalStatsItem = TotalStatsItem(),
    val ytdRunTotals: TotalStatsItem = TotalStatsItem()
)