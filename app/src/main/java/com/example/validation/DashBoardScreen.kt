package com.example.validation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Preview
@Composable
fun DashBoardScreen() {
    val dashboardViewModel: DashboardViewModel = viewModel()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(Color.White)
            .padding(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    dashboardViewModel.onRegistrationEvent(RegistrationEvent.Name(it))
                },
                /*isError = dashboardViewModel.errorName.value!!.status,*/
                supportingText = {
                    if (!dashboardViewModel.errorName.value!!.status) {
                        Text(text = dashboardViewModel.errorName.value!!.errorMessage)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(
                        text = "Name",
                        style = TextStyle(
                            fontStyle = FontStyle.Normal,
                            color = Color.Black
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    dashboardViewModel.onRegistrationEvent(RegistrationEvent.Email(it))
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(
                        text = "Email",
                        style = TextStyle(
                            fontStyle = FontStyle.Normal,
                            color = Color.Black
                        )
                    )
                },
                /*  isError = dashboardViewModel.errorEmail.value!!.status,*/
                supportingText = {
                    if (!dashboardViewModel.errorEmail.value!!.status) {
                        Text(text = dashboardViewModel.errorEmail.value!!.errorMessage)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

            )

            OutlinedTextField(
                value = number,
                onValueChange = {
                    number = it
                    dashboardViewModel.onRegistrationEvent(RegistrationEvent.Number(it))
                },
                /*isError = dashboardViewModel.errorNumber.value!!.status,*/
                supportingText = {
                    if (!dashboardViewModel.errorNumber.value!!.status) {
                        Text(text = dashboardViewModel.errorNumber.value!!.errorMessage)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                    Text(
                        text = "Number",
                        style = TextStyle(
                            fontStyle = FontStyle.Normal,
                            color = Color.Black
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            ElevatedButton(
                onClick = {
                    dashboardViewModel.onRegistrationEvent(RegistrationEvent.SaveClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(text = "Save")
            }
        }

    }
}