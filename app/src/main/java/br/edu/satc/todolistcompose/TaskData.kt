package br.edu.satc.todolistcompose

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
data class todo(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "task_name") val userName: String,
    @ColumnInfo(name = "task_name2") val userPhone: String
)

@Dao
interface todoDao {
    @Query("SELECT * FROM todo")
    fun getAll(): List<todo>

    @Query("SELECT * FROM todo WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<todo>

    @Query(
        "SELECT * FROM todo WHERE task_name LIKE :first AND " +
                "task_name2 LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): todo

    @Update
    fun updateAll(vararg users: todo)

    @Insert
    fun insertAll(vararg users: todo)

    @Delete
    fun delete(user: todo)
}

@Database(entities = [todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): todoDao
}