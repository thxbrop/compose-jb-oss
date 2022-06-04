package screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ui.drawer.DrawerBody
import ui.drawer.DrawerHeader
import ui.drawer.Item
import ui.image.CircleImage

private val drawerItems = listOf(
    Item(
        id = "home",
        title = "主页",
        icon = Icons.Rounded.Home,
        contentDescription = "前往主页"
    )
)

@Composable
fun MainPage(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {
    MaterialTheme {
        ModalDrawer(
            drawerState = scaffoldState.drawerState,
            drawerContent = {
                DrawerHeader {
                    CircleImage(
                        painter = painterResource("1.jpg"),
                        contentDescription = "Header",
                        modifier = Modifier.size(64.dp).clickable {

                        }
                    )
                    Text(
                        "Hello World",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp
                        ),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }
                DrawerBody(drawerItems) {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(it.title)
                    }
                }
            },
            drawerBackgroundColor = Color.DarkGray
        ) {

        }
    }
}