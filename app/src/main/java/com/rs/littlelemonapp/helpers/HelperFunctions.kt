package com.rs.littlelemonapp.helpers

import com.rs.littlelemonapp.data.AppDatabase
import com.rs.littlelemonapp.data.MenuItemNetwork
import com.rs.littlelemonapp.data.MenuNetwork
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

fun validateData(firstName: String, lastName: String, email: String): Boolean {
    var isValidated = false

    if(firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()) {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            isValidated = true
        }
    }
    return isValidated
}

suspend fun fetchMenu(url: String): List<MenuItemNetwork> {
    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }
    val httpResponse: MenuNetwork = httpClient.get(url).body()
    return httpResponse.items
}

fun saveMenuToDatabase(database: AppDatabase, menuItemsNetwork: List<MenuItemNetwork>) {
    val menuItemsDatabase = menuItemsNetwork.map { it.toMenuItemDatabase() }
    database.menuItemDAO().insertAll(*menuItemsDatabase.toTypedArray())
}