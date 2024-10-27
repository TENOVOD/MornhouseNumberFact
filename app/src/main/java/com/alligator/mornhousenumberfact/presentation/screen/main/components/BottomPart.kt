package com.alligator.mornhousenumberfact.presentation.screen.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alligator.mornhousenumberfact.data.model.NumberModel


@Composable
fun BottomPart(numbersList: List<NumberModel>, onClick: (NumberModel) -> Unit) {
    Spacer(Modifier.fillMaxWidth().height(1.dp).background(color = Color.Black))
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(numbersList) { entity->
            NumberCart(entity){
                onClick(it)
            }
            Spacer(Modifier.fillMaxWidth().height(1.dp).background(color = Color.LightGray))
        }
    }
}

@Composable
fun NumberCart(number: NumberModel, onClick:(NumberModel)->Unit) {
    Row(modifier = Modifier.fillMaxWidth().clickable(
        onClick = {
            onClick(number)
        }
        ,
        indication = ripple( ),
        interactionSource = remember { MutableInteractionSource() }
    ) .padding(horizontal = 16.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(0.2f),
            text = number.numberValue.toString(), style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = number.fact, style = TextStyle(
                fontSize = 14.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
