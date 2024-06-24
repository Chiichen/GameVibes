package cn.chiichen.gamevibes.ui.games

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Game(
    val id:Int,
    val image: String,
    val title:String,
    val rating: Double
)

class GamesViewModel:ViewModel() {
    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    private var _searchText = mutableStateOf("")
    val searchText = _searchText

    private val _relatedGames = MutableStateFlow<List<Game>>(emptyList())
    val relatedGame: StateFlow<List<Game>> = _relatedGames

    fun updateSearchText(newText:String){
        _searchText.value = newText
    }

    fun getRelatedGames(){
        //示例数据
        _relatedGames.value += listOf(
            Game(
                id = 10,
                image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                rating = 9.6,
                title = "title"
            ),
            Game(
                id = 10,
                image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                rating = 9.6,
                title = "title"
            ),
            Game(
                id = 10,
                image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                rating = 9.6,
                title = "title"
            ),
            Game(
                id = 10,
                image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                rating = 9.6,
                title = "title"
            ),
            Game(
                id = 10,
                image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                rating = 9.6,
                title = "title"
            ),
            Game(
                id = 10,
                image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                rating = 9.6,
                title = "title"
            ),
            Game(
                id = 10,
                image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                rating = 9.6,
                title = "title"
            ),
            Game(
                id = 10,
                image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                rating = 9.6,
                title = "title"
            ),
        )
    }

    fun loadGames(){
        viewModelScope.launch {
            if(_games.value.size < 100){
                _games.value += listOf(
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                    Game(
                        id = 10,
                        image = "https://nimg.ws.126.net/?url=http%3A%2F%2Fdingyue.ws.126.net%2F2023%2F1009%2F4027d398j00s29cad0144c000yg00j9m.jpg&thumbnail=660x2147483647&quality=80&type=jpg",
                        rating = 9.6,
                        title = "title"
                    ),
                )
            }
        }
    }
}