package com.suhun.guessutils

import android.content.Intent
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class Utils {
    val tag:String = Utils::class.java.simpleName

    fun getSaveResultString(contex:Context, fileName:String, saveKey:String):String?{
        return contex.getSharedPreferences(fileName, AppCompatActivity.MODE_PRIVATE).getString(saveKey, null)
    }

    fun saveResultString(contex: Context, fileName: String, saveKey: String, saveData:String){
        Log.d(tag, "Save data:$saveData")
        contex.getSharedPreferences(fileName, AppCompatActivity.MODE_PRIVATE)
            .edit()
            .putString(saveKey,saveData)
            .apply()
    }

    fun goToAvtivity(targetContext:Context, purpose:Class<*>){
        var intent:Intent = Intent(targetContext, purpose)
        targetContext.startActivity(intent)
    }
}