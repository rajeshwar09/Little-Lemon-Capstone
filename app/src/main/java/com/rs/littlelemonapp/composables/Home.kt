package com.rs.littlelemonapp.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.rs.littlelemonapp.data.ViewModel

@Composable
fun Home(navHostController: NavHostController) {
    val viewModel: ViewModel = viewModel()
    val databaseMenuItems = viewModel.getAllDatabaseMenuItems().observeAsState(emptyList()).value
    val search = remember {
        mutableStateOf("")
    }
    
    LaunchedEffect(key1 = Unit) {
        viewModel.getData()
    }
    
    Column {
        Header(navHostController = navHostController)
        UpperPanel { search.value = it }
        LowerPanel(databaseMenuItems = databaseMenuItems, search = search)
    }
}