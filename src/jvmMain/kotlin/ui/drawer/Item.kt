package ui.drawer

import androidx.compose.ui.graphics.vector.ImageVector

data class Item(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val contentDescription: String
)
