package com.alligator.mornhousenumberfact.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alligator.mornhousenumberfact.data.local.dao.NumbersDao
import com.alligator.mornhousenumberfact.data.model.NumberModel

@Database(
    entities = [NumberModel::class],
    version = 1
)
abstract class MainDb:RoomDatabase() {
    abstract fun numbersDao(): NumbersDao
}