package cn.chiichen.gamevibes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cn.chiichen.gamevibes.ui.MyApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        MyApp()
}
