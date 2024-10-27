package com.alligator.mornhousenumberfact.presentation.screen.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alligator.mornhousenumberfact.presentation.navigation.LocalNavController
import com.alligator.mornhousenumberfact.presentation.screen.detail.components.TopBar
import com.alligator.mornhousenumberfact.presentation.viewmodel.SharedViewModel

/*
   Second screen with information about number
 */

@Composable
fun DetailsScreen(
    sharedViewModel: SharedViewModel
) {
    val navController = LocalNavController.current
    val sharedNumber = sharedViewModel.getSharedData()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            TopBar(onClick = {
                navController.popBackStack()
            })
        }
    ) { innerPadding ->
        AnimatedVisibility(sharedNumber != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp, vertical = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {

                Text(
                    text = sharedNumber?.numberValue.toString(), style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = sharedNumber?.fact.toString(), style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}

