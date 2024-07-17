package com.rs.littlelemonapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.rs.littlelemonapp.helpers.fetchMenu
import com.rs.littlelemonapp.helpers.saveMenuToDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel (application: Application): AndroidViewModel(application) {
    private val database: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "database"
    ).build()

    fun getAllDatabaseMenuItems(): LiveData<List<MenuDatabase>> {
        return database.menuItemDAO().getAll()
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            saveMenuToDatabase(
                database,
                fetchMenu("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            )
        }
    }
}