package cn.chiichen.gamevibes.ui.common.login

import android.annotation.SuppressLint
import android.webkit.WebSettings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cn.chiichen.gamevibes.ui.common.webview.CustomWebView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LoginWebview(){

    var rememberWebViewProgress: Int by remember { mutableIntStateOf(-1) }
    Box {
        CustomWebView(
            modifier = Modifier.fillMaxSize(),
            url = "https://www.baidu.com/",
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
                    setSupportZoom(true)
                    builtInZoomControls = true
                    displayZoomControls = true
                    //是否支持通过JS打开新窗口
                    javaScriptCanOpenWindowsAutomatically = true
                    //不加载缓存内容
                    cacheMode = WebSettings.LOAD_NO_CACHE
                }
            }, onBack = { webView ->
                if (webView?.canGoBack() == true) {
                    webView.goBack()
                }
            }, onReceivedError = {
            }
        )
    }
}
