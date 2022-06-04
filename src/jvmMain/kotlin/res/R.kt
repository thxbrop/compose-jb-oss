package res

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

object R {
    object string {
        const val app_name = "在线购物系统"
        const val screen_login_title = "使用电子邮箱登录账号"
        const val screen_login_title_requesting = "网络请求中…"
        const val screen_login_label_email = "Email"
        const val screen_login_label_password = "密码"
        const val screen_login_button_login = "登录"
        const val screen_login_button_register = "注册"
    }

    object drawable {
        @Composable
        fun icon() = painterResource("telegram.svg")
    }
}