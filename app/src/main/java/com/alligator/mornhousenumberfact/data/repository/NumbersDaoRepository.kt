package com.alligator.mornhousenumberfact.data.repository

import com.alligator.mornhousenumberfact.data.local.dao.NumbersDao
import com.alligator.mornhousenumberfact.data.model.NumberModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NumbersDaoRepository @Inject constructor(
    private val numbersDao: NumbersDao
) {

    fun getAllNumbers(): Flow<List<NumberModel>>{
       return  numbersDao.getAllNumbers()
    }

    suspend fun getNumberById(id:Long): NumberModel {
        return numbersDao.getNumberById(id)
    }

    suspend fun insertNumber(number: NumberModel){
        numbersDao.insertNumber(number)
    }

}