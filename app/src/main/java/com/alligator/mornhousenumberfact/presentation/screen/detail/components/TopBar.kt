package com.alligator.mornhousenumberfact.presentation.screen.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/*
 Second screen top  bar
 */

@Composable
fun TopBar(onClick:()->Unit){
    Row(Modifier.fillMaxWidth().background(Color.DarkGray) .padding(horizontal = 16.dp, vertical = 8.dp )) {
        IconButton(onClick=onClick) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
        }
    }
}