package cn.chiichen.gamevibes.ui.settings.language

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
enum class Languages{
    zh_CN{
        override fun toFullName() = "简体中文"
         },
    en{
        override fun toFullName()="English"
      };
    abstract fun toFullName(): String
}
class LanguageViewModel :ViewModel(){
    val selectedLanguage = mutableStateOf(Languages.zh_CN);
}