package top.lightdev.reader.database

import androidx.room.Room
import androidx.room.RoomDatabase
import top.lightdev.reader.database.AppDatabase

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = NSHomeDirectory() + "/reader.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
        factory = { AppDatabase::class.instantiateImpl() }
    )
}