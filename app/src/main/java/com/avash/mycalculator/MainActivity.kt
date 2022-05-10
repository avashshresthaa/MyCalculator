package com.avash.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.Toast
import com.avash.mycalculator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {


    var lastNumeric:Boolean = false
    var lastDot: Boolean = false

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDigit(view: View){
        likeClear()
      binding.tvInput.append((view as Button).text)
        lastNumeric = true
      /*  if (binding.tvInput.text.contains("1"))
            binding.tvInput.text = "Haha"*/

    }

    fun onClear(view: View){
      binding.tvInput.text = ""
        lastNumeric = false
        lastDot = false
        likeClear()
    }
    fun onDecimalPoint(view: View){
        likeClear()
        if (lastNumeric && !lastDot){
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun like(view: View){
        var text = "Thank you for liking my app. :)"
        binding.tvInput1.setText(text)
    }

    fun likeClear(){
        binding.tvInput1.text = ""
    }

    fun onBack(view: View){
        likeClear()
        var del = binding.tvInput.text.toString()
        if (del.isEmpty()) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT)
        }
            else {
            del = del.substring(0, del.length - 1)
        }
        binding.tvInput.setText(del)
    }

    fun onEqual(view: View){
        likeClear()
        if (lastNumeric){
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1   )
                }

                if (tvValue.contains("-")){

                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }else if (tvValue.contains("/")){

                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }else if (tvValue.contains("+")){

                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }else if (tvValue.contains("*")){

                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) : String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)
            return value
            // 99.0 last two char will be . and 0
            // 0 is the first part of the string which is 9 another 9 is second, overall length is 4

    }

    fun onOperator(view: View){
        likeClear()
        if(lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())){
            binding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }

    }

    private fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }
    }


}