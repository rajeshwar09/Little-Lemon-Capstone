package com.rs.littlelemonapp.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rs.littlelemonapp.R
import com.rs.littlelemonapp.ui.theme.PrimaryGreen
import com.rs.littlelemonapp.ui.theme.PrimaryYellow


@Composable
fun UpperPanel(search: (param: String) -> Unit) {
    val searchPhrase = remember {
        mutableStateOf("")
    }

    Log.d("---Search---", "UpperPanel: ${searchPhrase.value}")

    Column(
        modifier = Modifier
            .background(PrimaryGreen)
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = R.string.little_lemon.toString(),
            style = MaterialTheme.typography.headlineLarge,
            color = PrimaryYellow
        )
        Text(
            text = R.string.city.toString(),
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = R.string.info.toString(),
                modifier = Modifier.fillMaxWidth(0.7f),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
            Image(
                painter = painterResource(id = R.drawable.image_1),
                contentDescription = "Image",
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(
            value = searchPhrase.value, onValueChange = {
                searchPhrase.value = it
                search(searchPhrase.value)
            },
            placeholder = { Text(text = R.string.enter_phrase.toString()) },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            colors = TextFieldDefaults.colors(MaterialTheme.colorScheme.background),
            modifier = Modifier.fillMaxWidth()
        )
    }
}