package ui.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileItem(
    item: Item,
    onclick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onclick)
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            text = item.value,
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            )
        )
        Text(
            text = item.title,
            style = TextStyle(
                color = Color.Gray,
                fontSize = 12.sp,
            ),
            modifier = Modifier.padding(top = 4.dp)
        )
    }

}