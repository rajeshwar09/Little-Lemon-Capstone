package com.rs.littlelemonapp.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rs.littlelemonapp.R
import com.rs.littlelemonapp.data.MenuDatabase
import com.rs.littlelemonapp.ui.theme.PrimaryGreen
import com.rs.littlelemonapp.ui.theme.Secondary2


@Composable
fun MenuCategories(categories: Set<String>, categoryLambda: (sel: String) -> Unit) {
    val category = remember {
        mutableStateOf("")
    }

    Card(elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
            Text(text = "Out For Delivery", fontWeight = FontWeight.Bold)

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CategoryButton(category = "All") {
                    category.value = it.lowercase()
                    categoryLambda(it.lowercase())
                }

                for (cat in categories) {
                    CategoryButton(category = cat) {
                        category.value = it
                        categoryLambda(it)
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryButton(category: String, selectedCategory: (cat: String) -> Unit) {
    val isClicked = remember {
        mutableStateOf(false)
    }

    Button(
        onClick = {
            isClicked.value = !isClicked.value
            selectedCategory(category)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Secondary2,
            contentColor = PrimaryGreen
        )
    ) {
        Text(text = category)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuDatabase) {
    val itemDescription = if (item.description.length > 100) {
        item.description.substring(0, 100) + "..."
    } else {
        item.description
    }

    Card(elevation = CardDefaults.cardElevation(4.dp), modifier = Modifier.clickable {}) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Text(text = itemDescription, modifier = Modifier.padding(bottom = 10.dp))
            Text(text = "$ ${item.price}", fontWeight = FontWeight.Bold)
        }

        GlideImage(
            model = item.imageUrl,
            contentDescription = "dish",
            modifier = Modifier.size(100.dp, 100.dp),
            contentScale = ContentScale.Crop
        )
    }
}