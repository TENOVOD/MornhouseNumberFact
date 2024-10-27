package com.alligator.mornhousenumberfact.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "numbers")
data class NumberModel(

    @PrimaryKey(autoGenerate = true)
    val id:Long,

    @ColumnInfo(name = "number_value")
    val numberValue:Int,

    @ColumnInfo(name = "fact")
    val fact:String
)