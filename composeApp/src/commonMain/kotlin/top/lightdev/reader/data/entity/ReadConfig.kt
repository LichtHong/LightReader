package top.lightdev.reader.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class ReadConfig(
    var reverseToc: Boolean = false,
    var pageAnim: Int? = null,
    var reSegment: Boolean = false,
    var imageStyle: String? = null,
    // 正文使用净化替换规则
    var useReplaceRule: Boolean? = null,
    //去除标签
    var delTag: Long = 0L,
    var ttsEngine: String? = null,
    var splitLongChapter: Boolean = true
)