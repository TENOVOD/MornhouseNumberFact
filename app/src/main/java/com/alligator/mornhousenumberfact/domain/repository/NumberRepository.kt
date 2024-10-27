package com.alligator.mornhousenumberfact.domain.repository

interface NumberRepository {

    suspend fun getFactAboutNumber(number: Int):String

    suspend fun getFactAboutRandomNumber():String

}