package com.javy.athlete.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javy.athlete.ui.model.TotalStatsItem

@Composable
fun TotalStats(label: String, statsItem: TotalStatsItem) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 12.dp)) {
        Text(text = label)
        Row(modifier = Modifier.padding(start = 16.dp, end = 12.dp)) {
            Text(text = "Count: ")
            Text(text = statsItem.count)
        }
        Row(modifier = Modifier.padding(start = 16.dp, end = 12.dp)) {
            Text(text = "Distance: ")
            Text(text = statsItem.distance)
        }
    }
}

@Preview
@Composable
fun TotalStatsPreview() {
    TotalStats("All Run Totals", TotalStatsItem())
}