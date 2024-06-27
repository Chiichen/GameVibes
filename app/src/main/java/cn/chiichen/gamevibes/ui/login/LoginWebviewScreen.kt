package cn.chiichen.gamevibes.ui.login

import android.annotation.SuppressLint
import android.net.Uri
import android.text.TextUtils
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
import com.tencent.mmkv.MMKV


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun LoginWebview(navController: NavController, viewModel: LoginViewModel = LoginViewModel()) {
    val loginUrl = viewModel.casdoor.getSignInUrl();
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
                    navController.popBackStack()
                }
            }, onReceivedError = { error ->
                Log.e("Login", String.format("LoginWebview error %s", error))
            }, overrideUrlLoading = { _, request ->
                val url = request?.url.toString()
                val uri = Uri.parse(url)
                if (uri.toString()
                        .startsWith(GameVibesConfig.casdoor_endpoint + "/login/oauth/" + GameVibesConfig.casdoor_redirectUri)
                ) {
                    val code: String? = uri.getQueryParameter("code")
                    code?.let { s ->
                        if (!TextUtils.isEmpty(code)) {
                            viewModel.getAccessToken(s);
                            viewModel.accessToken.value?.let { token ->
                                val mmkv = MMKV.defaultMMKV()
                                mmkv.putString("login_token", token)
                                navController.popBackStack();
                                return@CustomWebView true;
                            }
                            return@CustomWebView false;
                        }
                    }

                }
                return@CustomWebView false
            }
        )
    }
}
