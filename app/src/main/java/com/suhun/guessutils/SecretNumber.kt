package com.suhun.guessutils

import android.content.res.Resources
import android.util.Log
import java.util.Random

class SecretNumber {
    val tag:String = SecretNumber::class.java.simpleName
    var secretRandomo:Int
    var guessCounter:Int = 0

    init{
        secretRandomo = Random().nextInt(100) + 1
        Log.d(tag, "Secret number is $secretRandomo")
    }

    fun verify(userInput:Int) = secretRandomo - userInput

    fun verifyResult(r:Resources, userInput: Int):String{
        guessCounter++
        if(verify(userInput)>0){
            return "Bigger!!!"
        }else if(verify(userInput)<0){
            return "Smaller!!!"
        }else{
            if(guessCounter<3){
                return "Excellent! The number is $secretRandomo"
            }else{
                return "You got it!!!"
            }
        }
    }

    fun resetAll(){
        guessCounter = 0
        secretRandomo = Random().nextInt(100) + 1
        Log.d(tag, "Reset secret number is $secretRandomo")
    }
}