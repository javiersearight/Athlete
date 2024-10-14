package com.javy.athlete.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javy.athlete.ui.model.Stats

@Composable
fun Stats(stats: Stats) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Column {
            Text(text = "Stats")
            TotalStats(label = "Recent Run Totals", statsItem = stats.recentRunTotals)
            TotalStats(label = "All Run Totals", statsItem = stats.allRunTotals)
            TotalStats(label = "Recent Swim Totals", statsItem = stats.recentSwimTotals)
            TotalStats(label = "All Swim Totals", statsItem = stats.allSwimTotals)
            TotalStats(label = "YTD Run Totals", statsItem = stats.ytdRunTotals)
        }
    }
}