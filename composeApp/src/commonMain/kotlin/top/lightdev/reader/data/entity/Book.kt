package top.lightdev.reader.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import top.lightdev.reader.data.constant.BookType
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Serializable
@Entity(tableName = "book")
@OptIn(ExperimentalEncodingApi::class)
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    // 分组
    var groupId: Int? = null,
    // 类型（详见BookType）
    var type: Int = BookType.TEXT,
    // 书籍名称（书源获取）
    var name: String = "",
    // 作者名称（书源获取）
    var author: String = "",
    // 标签（书源获取）
    var tag: String? = null,
    // 封面 Base64（书源获取）
    var cover: String? = null,
    // 封面 Base64（自定义）
    var customCover: String? = null,
    // 简介（书源获取）
    var intro: String? = null,
    // 简介（自定义）
    var customIntro: String? = null,
    //字数
    var wordNum: String? = null,
    // 书籍目录总数
    var chapterNum: Int = 0,
    // 书源 URL
    var bookUrl: String = "",
    // 详情页 Url（本地书源存储完整文件路径）
    var infoUrl: String = "",
    // 目录页 Url (toc=Table of contents)
    var tocUrl: String = "",
    //书源名称 or 本地书籍文件名
    var originName: String = "",
    // 当前章节索引
    var curChapterIndex: Int = 0,
    // 当前章节名称
    var curChapterTitle: String? = null,
    // 当前章阅读的进度
    var curCharacterPos: Int = 0,
    // 最新章节标题
    var latestChapterTitle: String? = null,
    // 最近阅读时间
    var latestReadTime: Long = Clock.System.now().epochSeconds,
    // 最近更新时间
    var latestUpdateTime: Long = Clock.System.now().epochSeconds,
    // 书源排序
    var originOrder: Int = 0,
    // 自定义书籍变量信息 (用于书源规则检索书籍信息)
    var variable: String? = null,
    // 阅读设置
    @Embedded
    var readConfig: ReadConfig? = null,
    // 刷新书架时更新书籍信息
    var markread: Boolean = false,
    // 同步时间
    var syncTime: Long = 0L,
) {
    @Ignore
    val realCover: String? = customCover ?: cover

    @Ignore
    val realIntro: String = customIntro ?: (intro ?: "")

    @Ignore
    val readProgress: Float = if (markread) 1f else curChapterIndex.toFloat() / chapterNum
}
