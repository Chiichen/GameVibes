package cn.chiichen.gamevibes.ui.login

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebSettings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cn.chiichen.gamevibes.config.GameVibesConfig
import cn.chiichen.gamevibes.ui.common.webview.CustomWebView
import org.casdoor.Casdoor
import org.casdoor.CasdoorConfig


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LoginWebview(navController: NavController) {
    val casdoorConfig = CasdoorConfig(
        endpoint = GameVibesConfig.casdoor_endpoint,
        clientID = GameVibesConfig.casdoor_clientID,
        organizationName = GameVibesConfig.casdoor_organizationName,
        redirectUri = GameVibesConfig.casdoor_redirectUri,
        appName = GameVibesConfig.casdoor_appName,
    )
    Log.i("Login", String.format("LoginWebview: endpoint %s", casdoorConfig.endpoint))
    val casdoor = Casdoor(casdoorConfig)
    val loginUrl = casdoor.getSignInUrl(scope = "profile")
    Log.i("Login", String.format("LoginWebview: loginUrl %s", loginUrl))
    var rememberWebViewProgress: Int by remember { mutableIntStateOf(-1) }
    Box {
        CustomWebView(
            modifier = Modifier.fillMaxSize(),
            url = loginUrl,
            onProgressChange = { progress ->
                rememberWebViewProgress = progress
            },
            initSettings = { settings ->
                settings?.apply {
                    //支持js交互
                    javaScriptEnabled = true
                    //将图片调整到适合webView的大小
                    useWideViewPort = true
                    //缩放至屏幕的大小
                    loadWithOverviewMode = true
                    //缩放操作
                    setSupportZoom(false)
                    //是否支持通过JS打开新窗口
                    javaScriptCanOpenWindowsAutomatically = true
                    //不加载缓存内容
                    cacheMode = WebSettings.LOAD_NO_CACHE
                    domStorageEnabled = true
                }
            }, onBack = { webView ->
                if (webView?.canGoBack() == true) {
                    webView.goBack()
                } else {
                    navController.popBackStack();
                }
            }, onReceivedError = { error ->
                Log.e("Login", String.format("LoginWebview error %s", error))
            }
        )
    }
}
