package com.example.takada_kenshi_no_densetsu_android.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.takada_kenshi_no_densetsu_android.R
import com.example.takada_kenshi_no_densetsu_android.ui.densetsu.DensetsuScreen
import com.example.takada_kenshi_no_densetsu_android.ui.densetsuList.DensetsuListScreen
import kotlinx.coroutines.launch

@Composable
fun Home(
    playSound: (Int) -> Unit,
    stop: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = { DensetsuTopAppBar() },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            HomeCategoryTabs(
                playSound = playSound,
                showSnackBar = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.error_message),
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                stop = stop
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DensetsuTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        actions = {
            IconButton(onClick = {
                // navigate to information
            }) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "information",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeCategoryTabs(
    modifier: Modifier = Modifier,
    showSnackBar: () -> Unit,
    playSound: (Int) -> Unit,
    stop: () -> Unit
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
                    0 -> {
                        DensetsuScreen(
                            showSnackBar = showSnackBar,
                            playSound = playSound
                        )
                        stop()
                    }

                    1 -> {
                        DensetsuListScreen(playSound = playSound)
                        stop()
                    }
                }
            }
        }
    }
}

enum class TabItem {
    Home, List
}