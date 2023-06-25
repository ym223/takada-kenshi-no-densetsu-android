package com.example.takada_kenshi_no_densetsu_android.ui.densetsuList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.takada_kenshi_no_densetsu_android.data.Densetsu
import my.nanihadesuka.compose.LazyColumnScrollbar

@Composable
fun DensetsuListScreen(
    densetsuListViewModel: DensetsuListViewModel = hiltViewModel()
) {
    val densetsuListState = densetsuListViewModel.densetsuList.collectAsState()

    DensetsuList(
        densetsuList = densetsuListState.value,
        playSound = densetsuListViewModel::play
    )
}

@Composable
fun DensetsuList(
    densetsuList: List<Densetsu>,
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
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(densetsuList) {
                DensetsuListItem(no = it.no, text = it.text) {
                    playSound(it.no)
                }
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
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
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
                Text(text = "Densetsu No.%d".format(no))
                VoiceIcon(no = no, text = text, onClick = onClick)
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
    val densetsuText = text.ifEmpty { "???" }
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