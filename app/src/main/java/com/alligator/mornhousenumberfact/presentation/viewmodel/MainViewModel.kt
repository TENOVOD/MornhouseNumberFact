package com.alligator.mornhousenumberfact.presentation.viewmodel


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alligator.mornhousenumberfact.domain.repository.NumberRepository
import com.alligator.mornhousenumberfact.data.model.NumberModel
import com.alligator.mornhousenumberfact.core.network.ConnectivityObserver
import com.alligator.mornhousenumberfact.data.repository.NumbersDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val numberApi: NumberRepository,
    private val connectivityObserver: ConnectivityObserver,
    private val numbersDaoRepository: NumbersDaoRepository
) : ViewModel() {


    var sharedNumber: NumberModel? = null
    val networkStatus = connectivityObserver.status

    val numberValue = mutableStateOf("")
    val isIncorrectNumberValue = mutableStateOf(false)


    init {
        connectivityObserver.start()
        getAllNumberFromDb()
    }

    override fun onCleared() {
        super.onCleared()
        connectivityObserver.stop()
    }



    fun setNumber(number: String) {
        numberValue.value = number
        validationNumber()
    }
    fun clearNumber(){
        numberValue.value = ""
        isIncorrectNumberValue.value=false
    }


    /*
        Get data from remote server
     */
    fun getFactAboutRandomNumber(context: Context) {
        if (networkStatus.value == ConnectivityObserver.Status.Available) {
            viewModelScope.launch {
                val response = numberApi.getFactAboutRandomNumber()
                val numberEntity = getNumberEntityFromResponse(response)
                insertNumberInDb(numberEntity)
            }
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()
        }
    }

    fun getFactAboutCurrentNumber(context: Context) {
        validationNumber()
        if (networkStatus.value == ConnectivityObserver.Status.Available) {
            if(!isIncorrectNumberValue.value){
                viewModelScope.launch {
                    try {
                        val response = numberApi.getFactAboutNumber(numberValue.value.toInt())
                        val numberEntity = getNumberEntityFromResponse(response)
                        insertNumberInDb(numberEntity)
                    } catch (error: IOException) {
                        Log.e("MainViewModel", error.message.toString())
                    }
                }
            }else{
                Toast.makeText(context, "Empty or incorrect value", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show()
        }
    }


    /*
       Work with database
     */
    fun getAllNumberFromDb(): Flow<List<NumberModel>> {
        return numbersDaoRepository.getAllNumbers()
    }

    fun insertNumberInDb(numberEntity: NumberModel) = viewModelScope.launch {
        numbersDaoRepository.insertNumber(numberEntity)
    }

    fun getNumberById(id: Long) = viewModelScope.launch {
        sharedNumber = numbersDaoRepository.getNumberById(id)
    }

    fun validationNumber() {
        isIncorrectNumberValue.value = numberValue.value.toIntOrNull()== null|| numberValue.value==""
    }

    fun getNumberEntityFromResponse(response: String): NumberModel {
        val id = 0L
        var number = ""
        var message = ""
        var isNumberRecorded = false
        response.forEach { symbol ->
            if (symbol.isDigit() && !isNumberRecorded) {
                number += symbol
                message += symbol
            } else {
                if (number.isNotBlank()) {
                    isNumberRecorded = true
                    message += symbol
                } else {
                    message += symbol
                }
            }
        }
        return NumberModel(id, number.toInt(), message)

    }

}