package com.alligator.mornhousenumberfact.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.alligator.mornhousenumberfact.domain.model.SharedNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor ():ViewModel() {

    private val _sharedData = MutableStateFlow<SharedNumber?>(null)
    private val sharedData: StateFlow<SharedNumber?> get() = _sharedData

    fun setSharedData(data:SharedNumber){
        _sharedData.value = data
    }

    fun getSharedData():SharedNumber?{
        return sharedData.value
    }

}