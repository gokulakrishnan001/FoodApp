package com.example.foodapp.database

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase


@Entity
data class  MenuItemRoom(
    @PrimaryKey
    val id:Int,
    @ColumnInfo(name="title")
    val title:String,
    @ColumnInfo(name="description")
    val description:String,
    @ColumnInfo(name="price")
    val price:String,
    @ColumnInfo(name="image")
    val image:String,
    @ColumnInfo(name="category")
    val category:String

)


@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItemRoom")
    fun getAll(): LiveData<List<MenuItemRoom>>

    @Insert
    fun insertAll(vararg menuItems: MenuItemRoom)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItemRoom::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao
}