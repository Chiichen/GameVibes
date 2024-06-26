package cn.chiichen.gamevibes.model.response

import cn.chiichen.gamevibes.model.entities.Game

class GameResponse (
    val records: MutableList<Game>,
    val total: Int
)