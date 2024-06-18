package cn.chiichen.gamevibes.utils

import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun timeConvertor(postTime: String): String {
    // 解析传入的时间字符串
    val formatter = DateTimeFormatter.ISO_INSTANT
    val dateTime = LocalDateTime.ofInstant(Instant.from(formatter.parse(postTime)), ZoneId.systemDefault())

    // 获取当前时间
    val now = LocalDateTime.now()

    // 计算时间差
    val duration = Duration.between(dateTime, now)
    val daysDifference = duration.toDays()
    val sameYear = now.year == dateTime.year

    return when {
        daysDifference > 7 && !sameYear -> dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        daysDifference > 7 -> dateTime.format(DateTimeFormatter.ofPattern("MM-dd"))
        daysDifference > 2 -> "${daysDifference}天以前"
        daysDifference == 2L -> "前天"
        daysDifference == 1L -> "昨天"
        duration.toHours() > 0 -> "${duration.toHours()}小时以前"
        duration.toMinutes() > 0 -> "${duration.toMinutes()}分钟以前"
        else -> "刚刚"
    }
}