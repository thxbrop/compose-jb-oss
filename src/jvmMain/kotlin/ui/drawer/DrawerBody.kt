package ui.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerBody(
    items: List<Item>,
    onItemClick: (Item) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        itemsIndexed(items) { index, item ->
            DrawerItem(item) {
                onItemClick.invoke(item)
            }
            if (index != items.size - 1) {
                Divider()
            }
        }
    }
}

@Composable
private fun DrawerItem(
    item: Item,
    onclick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(64.dp)
            .clickable(onClick = onclick)
            .padding(horizontal = 18.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.contentDescription,
            tint = Color.DarkGray,
            modifier = Modifier.fillMaxHeight().width(36.dp)
        )

        Text(
            text = item.title,
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(start = 12.dp).weight(1f),
        )
    }
}