package ui.window

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import res.R

@Composable
fun ApplicationScope.loadingWindow(
    content: @Composable FrameWindowScope.() -> Unit
) = Window(
    onCloseRequest = ::exitApplication,
    resizable = false,
    alwaysOnTop = true,
    state = WindowState(
        width = 400.dp,
        height = 300.dp,
        position = WindowPosition.Aligned(Alignment.Center)
    ),
    undecorated = true,
    transparent = true,
    content = content
)

@Composable
fun ApplicationScope.simpleWindow(
    title: String,
    content: @Composable FrameWindowScope.() -> Unit
) = Window(
    onCloseRequest = ::exitApplication,
    undecorated = true,
    content = content,
    transparent = true,
    resizable = false,
    icon = R.drawable.icon(),
    title = title
)