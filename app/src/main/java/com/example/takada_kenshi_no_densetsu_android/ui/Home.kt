package com.example.takada_kenshi_no_densetsu_android.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.takada_kenshi_no_densetsu_android.ui.densetsu.DensetsuScreen
import com.example.takada_kenshi_no_densetsu_android.ui.densetsuList.DensetsuListScreen
import kotlinx.coroutines.launch

@Composable
fun Home() {
    HomeCategoryTabs()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeCategoryTabs(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            TabItem.values().forEachIndexed { index, title ->
                Tab(
                    text = { Text(title.name) },
                    selected = index == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
        HorizontalPager(
            pageCount = TabItem.values().size,
            state = pagerState
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when (it) {
                    0 -> DensetsuScreen()
                    1 -> DensetsuListScreen()
                }
            }
        }
    }
}

enum class TabItem {
    Home, List
}