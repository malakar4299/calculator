package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calculatorViewModel: CalculatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonEqual.setOnClickListener {view: View ->
            run {
                calculateValue()
            }
        }

        binding.buttonSqrt.setOnClickListener {view:View ->
            run {
                calculateSQRT()
            }
        }

        binding.buttonClearAll.setOnClickListener {view:View ->
            run {
                clearAll()
            }
        }
    }

    var newCalculation = true;
    var decimalAdded = false;
    var operation = "X";
    var oldNumber = "";

    fun enterValueEvent(view: View){
        if(newCalculation){
            binding.calculatorView.setText("");
        }
        newCalculation = false;
        val enteredButton = view as Button;

        var enteredValue:String = binding.calculatorView.text.toString();

        when(enteredButton.id){
            binding.button0.id -> {
                enteredValue+="0";
            }
            binding.button1.id -> {
                enteredValue+="1";
            }
            binding.button2.id -> {
                enteredValue+="2";
            }
            binding.button3.id -> {
                enteredValue+="3";
            }
            binding.button4.id -> {
                enteredValue+="4";
            }
            binding.button5.id -> {
                enteredValue+="5";
            }
            binding.button6.id -> {
                enteredValue+="6";
            }
            binding.button7.id -> {
                enteredValue+="7";
            }
            binding.button8.id -> {
                enteredValue+="8";
            }
            binding.button9.id -> {
                enteredValue+="9";
            }
            binding.buttonDecimal.id -> {
                if(!decimalAdded){
                    enteredValue+=".";
                    decimalAdded = true;
                }
            }
        }
        calculatorViewModel.setCalculatorViewValue(enteredValue);
        binding.calculatorView.setText(calculatorViewModel.calculatorViewData);
    }

    fun conductOperation(view: View){
        val selectedButton = view as Button;

        when(selectedButton.id){
            binding.buttonAdd.id -> {
                operation="+";
            }
            binding.buttonSubtact.id -> {
                operation="-";
            }
            binding.buttonMultiply.id -> {
                operation="*";
            }
            binding.buttonDivide.id -> {
                operation="/";
            }
            binding.buttonSqrt.id -> {
                operation = "sqrt";
            }
        }

        oldNumber = binding.calculatorView.text.toString();
        newCalculation = true;
        decimalAdded = false;
    }

    private fun calculateValue()
    {
        val newNumber = binding.calculatorView.text.toString()
        var finalNumber:Double?=null
        Log.d("Operation", newNumber.toString() + oldNumber.toString())
        when(operation)
        {
            "+"->
            {
                finalNumber=oldNumber.toDouble() + newNumber.toDouble()
            }
            "-"->
            {
                finalNumber=oldNumber.toDouble() - newNumber.toDouble()
            }
            "*"->
            {
                finalNumber=oldNumber.toDouble() * newNumber.toDouble()
            }
            "/"->
            {
                if(newNumber.toInt()==0){
                    finalNumber = null;
                }else{
                    finalNumber=oldNumber.toDouble() / newNumber.toDouble()
                }
            }
        }
        if(finalNumber==null && operation=="/"){
            calculatorViewModel.setCalculatorViewValue("INV DNMT")
            binding.calculatorView.setText(calculatorViewModel.calculatorViewData)
        }
        else{
            calculatorViewModel.setCalculatorViewValue(finalNumber.toString())
            binding.calculatorView.setText(calculatorViewModel.calculatorViewData)
        }
        newCalculation=true
    }

    private fun calculateSQRT()
    {
        val inputNumber = binding.calculatorView.text.toString().toDouble();
        if(inputNumber<0){
            calculatorViewModel.setCalculatorViewValue("INV INP")
            binding.calculatorView.setText(calculatorViewModel.calculatorViewData)
        }else{
            val number= Math.sqrt(binding.calculatorView.text.toString().toDouble())
            calculatorViewModel.setCalculatorViewValue(number.toString())
            binding.calculatorView.setText(calculatorViewModel.calculatorViewData)
        }
        newCalculation=true
    }

    private fun clearAll(){
        calculatorViewModel.setCalculatorViewValue("")
        binding.calculatorView.setText(calculatorViewModel.calculatorViewData)
        newCalculation = true;
        decimalAdded = false;
    }
}