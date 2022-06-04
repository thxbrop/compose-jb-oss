package ui.profile

import res.Light
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileGroup(
    group: Group,
    items: List<Item>,
    onItemClick: (Item) -> Unit
) {
    Column {
        Text(
            text = group.title,
            style = TextStyle(
                color = Light.Primary,
                fontSize = 14.sp
            ),
            modifier = Modifier.padding(
                top = 12.dp,
                bottom = 4.dp,
                start = 12.dp,
                end = 12.dp
            )
        )

        Column {
            items.forEachIndexed { index, item ->
                ProfileItem(item) {
                    onItemClick.invoke(item)
                }
                if (index != items.size - 1) {
                    Divider()
                }
            }
        }
    }
}