package com.example.takada_kenshi_no_densetsu_android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.takada_kenshi_no_densetsu_android.ui.densetsu.DensetsuScreen
import com.example.takada_kenshi_no_densetsu_android.ui.densetsuList.DensetsuListScreen

@Composable
fun Home() {
    HomeCategoryTabs()
}

@Composable
private fun HomeCategoryTabs(
    modifier: Modifier = Modifier
) {
    var tabSelected by remember { mutableStateOf(TabItem.Home) }

    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabSelected.ordinal) {
            TabItem.values().forEachIndexed { index, title ->
                Tab(
                    text = { Text(title.name) },
                    selected = tabSelected.ordinal == index,
                    onClick = { tabSelected = TabItem.values()[index] },
                )
            }
        }
        when (tabSelected) {
            TabItem.Home -> DensetsuScreen()
            TabItem.List -> DensetsuListScreen()
        }
    }
}

enum class TabItem {
    Home, List
}