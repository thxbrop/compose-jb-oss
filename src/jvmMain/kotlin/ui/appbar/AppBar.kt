package ui.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.WindowScope

@Composable
fun endButtonGroup(
    applicationScope: ApplicationScope,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxSize().padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        AppBarButton(
            imageVector = Icons.Rounded.Close,
            contentDescription = "Close"
        ) {
            applicationScope.exitApplication()
        }
    }
}

@Composable
fun startButtonGroup(
    modifier: Modifier = Modifier,
    onclick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxSize().padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        AppBarButton(
            imageVector = Icons.Rounded.Menu,
            contentDescription = "Menu",
            action = onclick
        )
    }
}

@Composable
private fun AppBarButton(
    imageVector: ImageVector,
    contentDescription: String,
    action: () -> Unit,
) {
    Image(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = Modifier.padding(2.dp).clickable { action() }.size(24.dp),
        colorFilter = ColorFilter.tint(Color.LightGray),
        alpha = 0.8f,
    )
}


@Composable
fun WindowScope.AppWindowTitleBar(content: @Composable BoxScope.() -> Unit) = WindowDraggableArea {
    Box(Modifier.fillMaxWidth().height(48.dp).background(Color.DarkGray), content = content)
}