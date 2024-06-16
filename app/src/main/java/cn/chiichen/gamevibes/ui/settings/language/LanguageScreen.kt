package cn.chiichen.gamevibes.ui.settings.language


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LanguageScreen(navController: NavController) {
    val languageViewModel = LanguageViewModel()
    Column {
        for (item in Languages.entries) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = item.toFullName())
                IconToggleButton(
                    checked = languageViewModel.selectedLanguage.value == item,
                    onCheckedChange = { languageViewModel.selectedLanguage.value = item }) {
                    if (languageViewModel.selectedLanguage.value == item) {
                        Icon(
                            Icons.Rounded.Done,
                            contentDescription = "SelectedToggleItem"
                        )
                    } else {
                        //TODO empty circle
                    }


                }
            }
        }
//        RadioButton(selected = languageViewModel.selectedLanguage.value == Languages.en, onClick = { /*TODO*/ })
//        RadioButton(selected = languageViewModel.selectedLanguage.value == Languages.en, onClick = { /*TODO*/ })
    }

}

@Preview
@Composable
fun LanguageScreenPreview() {
    val mockNavController = rememberNavController()
    LanguageScreen(mockNavController)
}

