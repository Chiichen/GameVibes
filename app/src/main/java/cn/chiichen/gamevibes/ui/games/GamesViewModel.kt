package cn.chiichen.gamevibes.ui.games

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.chiichen.gamevibes.model.entities.Game
import cn.chiichen.gamevibes.model.request.PageRequest
import cn.chiichen.gamevibes.model.request.SearchRequest
import cn.chiichen.gamevibes.model.response.BaseResponse
import cn.chiichen.gamevibes.model.response.GameResponse
import cn.chiichen.gamevibes.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesViewModel : ViewModel() {
    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games = _games

    private var _searchText = mutableStateOf("")
    val searchText = _searchText

    private val _relatedGames = MutableStateFlow<List<Game>>(emptyList())
    val relatedGames: StateFlow<List<Game>> = _relatedGames

    var currentPage1 = 1
    var currentPage2 = 1

    fun updateSearchText(newText: String) {
        _searchText.value = newText
    }

    fun getRelatedGames() {
        viewModelScope.launch {
            try {
                currentPage2 = 1
                val call = RetrofitClient.gameApiService.getGameSearch(SearchRequest(currentPage2,10,_searchText.value))
                call.enqueue(object : Callback<BaseResponse<GameResponse>> {
                    override fun onResponse(call: Call<BaseResponse<GameResponse>>, response: Response<BaseResponse<GameResponse>>) {
                        if (response.isSuccessful) {
                            try {
                                // 尝试解析响应体
                                val responseBody = response.body()
                                if (responseBody != null && responseBody.code == 0) {
                                    _relatedGames.value = responseBody.data.records
                                    currentPage2++
                                } else {
                                    _relatedGames.value = emptyList()
                                }
                            } catch (e: Exception) {
                                Log.e("getRelatedGames", "Parsing error", e)
                            }
                        } else {
                            // 打印原始响应字符串
                            val errorBody = response.errorBody()?.string()
                            Log.e("getRelatedGames", "Error response: $errorBody")
                            _relatedGames.value = emptyList()
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<GameResponse>>, t: Throwable) {
                        _relatedGames.value = emptyList()
                        Log.e("getRelatedGames", "Request failed", t)
                    }
                })
            } catch (e: Exception) {
                _relatedGames.value = emptyList()
                Log.e("getRelatedGames", "Exception caught", e)
            }

        }
    }

    fun getMoreRelatedGames() {
        viewModelScope.launch {
            try {
                val call = RetrofitClient.gameApiService.getGameSearch(SearchRequest(currentPage2,10,_searchText.value))
                call.enqueue(object : Callback<BaseResponse<GameResponse>> {
                    override fun onResponse(call: Call<BaseResponse<GameResponse>>, response: Response<BaseResponse<GameResponse>>) {
                        if (response.isSuccessful) {
                            try {
                                // 尝试解析响应体
                                val responseBody = response.body()
                                if (responseBody != null && responseBody.code == 0) {
                                    _relatedGames.value += responseBody.data.records
                                    currentPage2++
                                } else {
                                    _relatedGames.value = emptyList()
                                }
                            } catch (e: Exception) {
                                Log.e("getRelatedGames", "Parsing error", e)
                            }
                        } else {
                            // 打印原始响应字符串
                            val errorBody = response.errorBody()?.string()
                            Log.e("getRelatedGames", "Error response: $errorBody")
                            _relatedGames.value = emptyList()
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<GameResponse>>, t: Throwable) {
                        _relatedGames.value = emptyList()
                        Log.e("getRelatedGames", "Request failed", t)
                    }
                })
            } catch (e: Exception) {
                _relatedGames.value = emptyList()
                Log.e("getRelatedGames", "Exception caught", e)
            }

        }
    }

    fun loadGames() {
        viewModelScope.launch {
            try {
                val call = RetrofitClient.gameApiService.getGameList(PageRequest(currentPage1,10))
                call.enqueue(object : Callback<BaseResponse<GameResponse>> {
                    override fun onResponse(call: Call<BaseResponse<GameResponse>>, response: Response<BaseResponse<GameResponse>>) {
                        if (response.isSuccessful) {
                            try {
                                // 尝试解析响应体
                                val responseBody = response.body()
                                if (responseBody != null && responseBody.code == 0) {
                                    _games.value += responseBody.data.records
                                    currentPage1 ++
                                } else {
                                    _games.value = emptyList()
                                }
                            } catch (e: Exception) {
                                Log.e("loadGames", "Parsing error", e)
                            }
                        } else {
                            // 打印原始响应字符串
                            val errorBody = response.errorBody()?.string()
                            Log.e("loadGames", "Error response: $errorBody")
                            _games.value = emptyList()
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<GameResponse>>, t: Throwable) {
                        _games.value = emptyList()
                        Log.e("loadGames", "Request failed", t)
                    }
                })
            } catch (e: Exception) {
                _games.value = emptyList()
                Log.e("loadGames", "Exception caught", e)
            }
        }
    }
}
