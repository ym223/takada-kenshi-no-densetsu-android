package com.example.takada_kenshi_no_densetsu_android.ui.densetsuList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.takada_kenshi_no_densetsu_android.R
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import com.example.takada_kenshi_no_densetsu_android.data.Param.MAX_DENSETSU
import my.nanihadesuka.compose.LazyColumnScrollbar

@Composable
fun DensetsuListScreen(
    densetsuListViewModel: DensetsuListViewModel = hiltViewModel(),
    playSound: (Int) -> Unit
) {
    val densetsuListState = densetsuListViewModel.densetsuList.collectAsState()
    var isShow by rememberSaveable {
        mutableStateOf(true)
    }

    DensetsuContent(
        densetsuList = densetsuListState.value,
        isShow = isShow,
        onStateChangedTrue = { isShow = true },
        onStateChangedFalse = { isShow = false },
        playSound = playSound
    )
}

@Composable
fun DensetsuContent(
    densetsuList: List<Densetsu>,
    isShow: Boolean,
    onStateChangedTrue: (Boolean) -> Unit,
    onStateChangedFalse: (Boolean) -> Unit,
    playSound: (Int) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .height(36.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                ChangeListStateText(
                    text = stringResource(id = R.string.show_all),
                    isShow = isShow,
                    onStateChanged = onStateChangedTrue
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "|")
                Spacer(modifier = Modifier.width(4.dp))
                ChangeListStateText(
                    text = stringResource(id = R.string.show_acquired),
                    isShow = isShow,
                    onStateChanged = onStateChangedFalse
                )
            }
            val densetsuListSize = densetsuList.filter { it.text.isNotEmpty() }.size
            Text(
                text = stringResource(id = R.string.densetsu_count).format(
                    densetsuListSize,
                    MAX_DENSETSU
                )
            )
        }
        DensetsuList(densetsuList = densetsuList, isShow = isShow, playSound = playSound)
    }
}

@Composable
fun ChangeListStateText(
    text: String,
    isShow: Boolean,
    onStateChanged: (Boolean) -> Unit
) {
    Text(text = text, modifier = Modifier.clickable {
        onStateChanged(isShow)
    })
}

@Composable
fun DensetsuList(
    densetsuList: List<Densetsu>,
    isShow: Boolean,
    playSound: (Int) -> Unit
) {
    val listState = rememberLazyListState()
    LazyColumnScrollbar(
        listState = listState,
        thumbColor = MaterialTheme.colorScheme.tertiary,
        thumbSelectedColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(horizontal = 8.dp),
        ) {
            items(densetsuList) {
                AnimatedVisibility(
                    visible = isShow || it.text.isNotEmpty(),
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        DensetsuListItem(no = it.no, text = it.text) {
                            playSound(it.no)
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun DensetsuListItem(
    no: Int,
    text: String,
    onClick: () -> Unit
) {
    // 点の横幅
    val onInterval = 5f
    // 点の感覚
    val offInterval = 10f

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.densetsu_no).format(no),
                    fontWeight = FontWeight.Bold
                )
                VoiceIcon(no = no, text = text, onClick = onClick)
            }
            Canvas(modifier = Modifier.fillMaxWidth()) {
                drawRoundRect(
                    color = Color.Gray,
                    cornerRadius = CornerRadius(1f),
                    style = Stroke(
                        width = 1f,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(onInterval, offInterval),
                            phase = onInterval + offInterval,
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            DensetsuText(text = text)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun VoiceIcon(
    no: Int,
    text: String,
    onClick: () -> Unit
) {
    if (text.isEmpty()) return
    if ((no <= 0) || (no >= 81)) return

    IconButton(onClick = onClick) {
        Icon(imageVector = Icons.Filled.VolumeUp, contentDescription = "read aloud")
    }
}

@Composable
fun DensetsuText(
    modifier: Modifier = Modifier,
    text: String
) {
    val densetsuText = text.ifEmpty { stringResource(id = R.string.densetsu_not_found) }
    Text(text = densetsuText, modifier = modifier, lineHeight = 1.8.em)
}

@Preview
@Composable
fun DensetsuListItemPreview() {
    Column {
        DensetsuListItem(
            no = 0,
            text = ""
        ) {

        }
        Spacer(modifier = Modifier.height(40.dp))
        DensetsuListItem(
            no = 1,
            text = "虫捕りをしていた少年が庭の石を持ち上げると、石の下には高田健志の顔をした芋虫がうごめいていた。気味が悪くなった彼は、石を芋虫に叩きつけて、部屋に逃げ帰った。石の下からは緑色の体液が、彼の跡を追うように流れ出していた。翌朝、彼は顔を洗おうと蛇口をひねった。水は綺麗な緑色をしていた。"
        ) {

        }
    }
}