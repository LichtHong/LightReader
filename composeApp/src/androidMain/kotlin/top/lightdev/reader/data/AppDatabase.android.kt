package top.lightdev.reader.data

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import top.lightdev.reader.applicationContext


actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFile = applicationContext.getDatabasePath("reader.db")
    return Room.databaseBuilder<AppDatabase>(
        context = applicationContext,
        name = dbFile.absolutePath
    )
}