import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import res.Light
import res.R
import screen.login.LoginScreen
import ui.LoadingPage
import ui.appbar.AppWindowTitleBar
import ui.appbar.endButtonGroup
import ui.appbar.startButtonGroup
import ui.window.loadingWindow
import ui.window.simpleWindow

fun main() = application {
    var isPerformingTask by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(2000)
        isPerformingTask = false
    }
    if (isPerformingTask) loadingWindow { LoadingPage() }
    else simpleWindow(R.string.app_name) {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState(
            drawerState = DrawerState(DrawerValue.Closed),
            snackbarHostState = SnackbarHostState()
        )
        MaterialTheme(
            colors = Colors(
                primary = Light.Primary,
                primaryVariant = Light.PrimaryContainer,
                secondary = Light.Secondary,
                secondaryVariant = Light.SecondaryContainer,
                onPrimary = Light.OnPrimary,
                onSecondary = Light.OnSecondary,
                isLight = true,
                surface = Light.Surface,
                onSurface = Light.OnSurface,
                background = Light.Background,
                onBackground = Light.OnBackground,
                error = Light.Error,
                onError = Light.OnError
            )
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    1.dp,
                    Color.Gray
                )
            ) {
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppWindowTitleBar {
                            startButtonGroup {
                                scope.launch {
                                    val drawerState = scaffoldState.drawerState
                                    if (drawerState.isClosed) drawerState.open()
                                    else drawerState.close()
                                }
                            }
                            endButtonGroup(this@application)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
//                MainPage(
//                    drawerItems = drawerItems,
//                    scaffoldState = scaffoldState,
//                    scope = scope
//                )
//                ProfilePage(
//                    scaffoldState = scaffoldState
//                )
                    LoginScreen(scaffoldState)
                }
            }
        }
    }
}
