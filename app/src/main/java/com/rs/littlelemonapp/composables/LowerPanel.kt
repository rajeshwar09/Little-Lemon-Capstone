package com.rs.littlelemonapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rs.littlelemonapp.data.MenuDatabase

@Composable
fun LowerPanel(databaseMenuItems: List<MenuDatabase>, search: MutableState<String>) {
    val categories = databaseMenuItems.map {
        it.category.replaceFirstChar { char ->
            char.uppercase()
        }
    }.toSet()

    val selectedCategory = remember {
        mutableStateOf("")
    }
    val items = if (search.value == "") {
        databaseMenuItems
    } else {
        databaseMenuItems.filter {
            it.title.contains(search.value, ignoreCase = true)
        }
    }

    Column {
        MenuCategories(categories = categories) {
            selectedCategory.value = it
        }
        Spacer(modifier = Modifier.size(2.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            for (item in items) {
                MenuItem(item = item)
            }
        }
    }
}