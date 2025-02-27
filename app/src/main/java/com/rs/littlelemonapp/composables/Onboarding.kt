package com.rs.littlelemonapp.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rs.littlelemonapp.R
import com.rs.littlelemonapp.helpers.rememberImeState
import com.rs.littlelemonapp.helpers.validateData
import com.rs.littlelemonapp.navigation.Home
import com.rs.littlelemonapp.navigation.Onboarding
import com.rs.littlelemonapp.ui.theme.PrimaryGreen


@Composable
fun Onboarding(context: Context, navHostController: NavHostController) {
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.scrollTo(scrollState.maxValue)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.6f)) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo"
            )
        }
        Row(modifier = Modifier.height(150.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Let's get to know you",
                style = MaterialTheme.typography.headlineLarge,
                color = PrimaryGreen
            )
        }

        Text(
            text = "Personal Information",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = firstName.value, onValueChange = { firstName.value = it },
            label = { Text(text = "First Name") },
            singleLine = true,
            placeholder = { Text(text = "Rudra") },
            colors = TextFieldDefaults.colors(PrimaryGreen),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName.value, onValueChange = { lastName.value = it },
            label = { Text(text = "Last Name") },
            singleLine = true,
            placeholder = { Text(text = "Bhairava") },
            colors = TextFieldDefaults.colors(PrimaryGreen),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email.value, onValueChange = { email.value = it },
            label = { Text(text = "Email") },
            singleLine = true,
            placeholder = { Text(text = "xyz@xyz.com") },
            colors = TextFieldDefaults.colors(PrimaryGreen),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(40.dp))

        Button(
            onClick = {
                if (validateData(firstName.value, lastName.value, email.value)) {
                    sharedPreferences.edit()
                        .putString("firstName", firstName.value)
                        .putString("lastName", lastName.value)
                        .putString("email", email.value)
                        .putBoolean("userRegistered", true)
                        .apply()

                    Toast.makeText(context, "Registration Successful", Toast.LENGTH_LONG).show()

                    navHostController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                        launchSingleTop = true
                    }
                } else {
                    Toast.makeText(context, "Wrong data", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Register")
        }
    }
}