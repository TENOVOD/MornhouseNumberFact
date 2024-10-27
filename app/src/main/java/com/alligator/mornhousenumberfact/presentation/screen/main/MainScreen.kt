package com.alligator.mornhousenumberfact.presentation.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.alligator.mornhousenumberfact.domain.model.SharedNumber
import com.alligator.mornhousenumberfact.presentation.navigation.LocalNavController
import com.alligator.mornhousenumberfact.presentation.navigation.Screen
import com.alligator.mornhousenumberfact.presentation.screen.main.components.BottomPart
import com.alligator.mornhousenumberfact.presentation.screen.main.components.TopPart
import com.alligator.mornhousenumberfact.presentation.viewmodel.MainViewModel
import com.alligator.mornhousenumberfact.presentation.viewmodel.SharedViewModel

@Composable
fun MainScreen(
    vm: MainViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current
    val number by vm.numberValue
    val isIncorrectNumberValue by vm.isIncorrectNumberValue
    val numbersList by vm.getAllNumberFromDb() .collectAsState(initial = emptyList())
    val navController = LocalNavController.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.Center
            ) {
                TopPart(
                    numberValue = number,
                    isIncorrectNumberValue = isIncorrectNumberValue,
                    onChangeNumberValue = { vm.setNumber(it) },
                    onClickGetFactByNumber = { vm.getFactAboutCurrentNumber(context) },
                    onClickGetRandomFact = { vm.getFactAboutRandomNumber(context) },
                    onClearTextField = {vm.clearNumber()}
                )
            }
            Box(modifier = Modifier.fillMaxSize()) {
                BottomPart(numbersList=numbersList){
                    sharedViewModel.setSharedData(SharedNumber(
                        it.numberValue,
                        it.fact
                    ))
                    navController.navigate(Screen.DETAILS_SCREEN.name)
                }
            }
        }
    }

}

