package com.example.calculator

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "CalculatorViewModel"
const val calculatorInitialView = ""

class CalculatorViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var calculatorViewData:String
        get() = savedStateHandle.get(calculatorInitialView) ?: ""
        set(value) = savedStateHandle.set(calculatorInitialView, value)


    fun setCalculatorViewValue(value: String){
        calculatorViewData = value;
    }

}