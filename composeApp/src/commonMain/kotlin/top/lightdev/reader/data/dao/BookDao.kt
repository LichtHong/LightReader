package top.lightdev.reader.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import top.lightdev.reader.data.entity.Book
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg book: Book)

    @Delete
    suspend fun delete(vararg book: Book)

    @Update
    suspend fun update(vararg book: Book)

    @Query("select * from book order by latestReadTime desc")
    fun getAllBooksFlow(): Flow<List<Book>>

    @Query("select * from book where curChapterIndex == 0 and curCharacterPos == 0 order by latestReadTime desc")
    fun getUnreadBooksFlow(): Flow<List<Book>>

    @Query("select * from book where markread == false and (curChapterIndex > 0 or curCharacterPos > 0) order by latestReadTime desc")
    fun getReadingBooksFlow(): Flow<List<Book>>

    @Query("select * from book where markread == true order by latestReadTime desc")
    fun getMarkreadBooksFlow(): Flow<List<Book>>

    @Query("select * from book order by latestReadTime desc")
    fun getAllBooksPagingSource(): PagingSource<Int, Book>

    @Query("select * from book where curChapterIndex == 0 and curCharacterPos == 0 order by latestReadTime desc")
    fun getUnreadBooksPagingSource(): PagingSource<Int, Book>

    @Query("select * from book where markread == false and (curChapterIndex > 0 or curCharacterPos > 0) order by latestReadTime desc")
    fun getReadingBooksPagingSource(): PagingSource<Int, Book>

    @Query("select * from book where markread == true order by latestReadTime desc")
    fun getMarkreadBooksPagingSource(): PagingSource<Int, Book>

}