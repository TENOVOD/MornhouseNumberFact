package com.alligator.mornhousenumberfact.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alligator.mornhousenumberfact.data.model.NumberModel
import kotlinx.coroutines.flow.Flow


@Dao
interface NumbersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumber(number: NumberModel)

    @Query("SELECT*FROM numbers ORDER BY id DESC")
    fun getAllNumbers(): Flow<List<NumberModel>>

    @Query("SELECT*FROM numbers WHERE id=:id")
    suspend fun getNumberById(id:Long): NumberModel
}