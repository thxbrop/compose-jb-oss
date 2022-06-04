package screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollbarAdapter
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ui.profile.Group
import ui.profile.Item
import ui.profile.ProfileGroup

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfilePage(
    scaffoldState: ScaffoldState
) {
    val profileGroups = listOf(
        Group("account", "账号"),
        Group("settings", "设置"),
        Group("help", "帮助"),
        Group("debug", "调试"),
    )

    val profileItems = listOf(
        Item("username", "account", "用户名", "@sortBy"),
        Item("phone", "account", "手机号码", "+86 176-2297-1229"),
        Item("profile", "account", "个人简介", "🦌"),
        Item("username", "settings", "用户名", "@sortBy"),
        Item("phone", "settings", "手机号码", "+86 176-2297-1229"),
        Item("profile", "settings", "个人简介", "🦌"),
        Item("username", "help", "用户名", "@sortBy"),
        Item("phone", "help", "手机号码", "+86 176-2297-1229"),
        Item("profile", "help", "个人简介", "🦌"),
        Item("username", "debug", "用户名", "@sortBy"),
        Item("phone", "debug", "手机号码", "+86 176-2297-1229"),
        Item("profile", "debug", "个人简介", "🦌"),
    )
    val scope = rememberCoroutineScope()
    Row {
        Card(
            shape = RoundedCornerShape(8.dp), modifier = Modifier.padding(16.dp).weight(3f)
        ) {
            Image(
                painterResource("1.jpg"),
                contentDescription = null,
                modifier = Modifier.fillMaxHeight(),
                contentScale = ContentScale.FillBounds
            )
        }
        Column(
            modifier = Modifier.weight(2f)
        ) {
            val scrollState = rememberLazyListState()
            Row {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(end = 4.dp), state = scrollState
                    ) {
                        items(profileGroups.size) { index ->
                            val group = profileGroups[index]
                            val items = profileItems.filter { it.groupId == group.id }
                            ProfileGroup(group, items) {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(it.title)
                                }
                            }
                        }
                    }

                    if (scrollState.firstVisibleItemScrollOffset > 0) {
                        Column(
                            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Divider(
                                color = Color.Transparent, modifier = Modifier.padding(vertical = 12.dp)
                            )
                            Card(shape = RoundedCornerShape(50),
                                backgroundColor = Color.White,
                                elevation = 4.dp,
                                onClick = {
                                    scope.launch {
                                        scrollState.animateScrollToItem(0)
                                    }
                                }) {
                                Text(
                                    text = "回到顶部", color = Color.Black, modifier = Modifier.padding(8.dp)
                                )
                            }
                        }

                    }
                }

                VerticalScrollbar(
                    modifier = Modifier.padding(
                        end = 8.dp,
                        top = 8.dp,
                        bottom = 8.dp,
                    ).width(8.dp),
                    adapter = ScrollbarAdapter(scrollState)
                )
            }
        }
    }
}