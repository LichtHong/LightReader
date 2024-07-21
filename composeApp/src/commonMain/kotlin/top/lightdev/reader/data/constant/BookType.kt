package top.lightdev.reader.data.constant

object BookType {
    const val TEXT = 0b1000 // 8 文本
    const val ERROR = 0b10000 // 16 更新失败
    const val AUDIO = 0b100000 // 32 音频
    const val IMAGE = 0b1000000 // 64 图片
    const val FILE = 0b10000000 // 128 只提供下载服务的网站
    const val LOCAL = 0b100000000 // 256 本地
    const val ARCHIVE = 0b1000000000 // 512 压缩包 表明书籍文件是从压缩包内解压来的
}