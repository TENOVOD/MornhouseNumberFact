package com.alligator.mornhousenumberfact.data.repository

import com.alligator.mornhousenumberfact.domain.repository.NumberRepository
import com.alligator.mornhousenumberfact.utils.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject


class NumberRepositoryImpl @Inject constructor (
    private val client: HttpClient
): NumberRepository {

    override suspend fun getFactAboutNumber(number: Int):String {
        return client.get("$BASE_URL/$number").body()
    }

    override suspend fun getFactAboutRandomNumber():String {
        return client.get("$BASE_URL/random/math").body()
    }
}