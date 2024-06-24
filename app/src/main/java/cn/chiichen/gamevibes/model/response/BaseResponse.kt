package cn.chiichen.gamevibes.model.response

data class BaseResponse<T>(val code: Int, val data: T, val message: String);