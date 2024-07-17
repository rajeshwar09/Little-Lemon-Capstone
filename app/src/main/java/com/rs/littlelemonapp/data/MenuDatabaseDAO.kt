package com.rs.littlelemonapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface MenuDatabaseDAO {
    @Query("SELECT * FROM MenuDatabase")
    fun getAll(): LiveData<List<MenuDatabase>>

    @Insert
    fun insertAll(vararg menuItems: MenuDatabase)
}

@Database(entities = [MenuDatabase::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun menuItemDAO(): MenuDatabaseDAO
}