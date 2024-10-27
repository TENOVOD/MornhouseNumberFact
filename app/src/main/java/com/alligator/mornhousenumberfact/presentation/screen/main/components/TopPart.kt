package com.alligator.mornhousenumberfact.presentation.screen.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopPart(
    numberValue: String,
    isIncorrectNumberValue: Boolean,
    onChangeNumberValue: (String) -> Unit,
    onClickGetFactByNumber: () -> Unit,
    onClickGetRandomFact: () -> Unit,
    onClearTextField:()->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        MainTextField(
            numberValue = numberValue,
            isIncorrectNumberValue = isIncorrectNumberValue,
            onChangeNumberValue = onChangeNumberValue,
            onClearTextField = onClearTextField
        )
        Button(onClick = onClickGetFactByNumber) {
            Text(text = "Get Fact About $numberValue")
        }
        Button(onClick = onClickGetRandomFact) {
            Text(text = "Get Random Fact")
        }
    }
}


@Composable
fun MainTextField(
    numberValue: String,
    isIncorrectNumberValue: Boolean,
    onChangeNumberValue: (String) -> Unit,
    onClearTextField: () -> Unit
) {
    OutlinedTextField(
        value = numberValue,
        onValueChange = onChangeNumberValue,
        isError = isIncorrectNumberValue,
        trailingIcon = {
            TextButton(onClick = {
                onClearTextField()
            }) {
                Icon(Icons.Filled.Clear, contentDescription = null)
            }
        },
        supportingText = {
            AnimatedVisibility(isIncorrectNumberValue) {
                Text(
                    text = "Input only integer", style = TextStyle(
                        fontSize = 10.sp,
                        color = Color.Red
                    )
                )
            }
        },
        label = {
            Text(text = "Integer")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}