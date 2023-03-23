package com.suhun.guessutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.suhun.guessutils.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    val tag:String = RecordActivity::class.java.simpleName
    val utils:Utils = Utils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun saveGame(view: View){
        val scoreCounter:Int = intent.getIntExtra("COUNT", -1)
        val player:String = binding.playNameInput.text.toString()
        val saveKey:String = "NAME_SCORE"
        val fileName:String = "save_guess_score"
        var saveData:String = "$player \t $scoreCounter"
        var saveResult = utils.getSaveResultString(this,fileName, saveKey)
        if(saveResult == null){
            Log.d(tag,"Save result is null")
            utils.saveResultString(this, fileName, saveKey, saveData)
        }else{
            Log.d(tag,"Already have data:$saveResult")
            var tempSave:String = "$saveData \n $saveResult"
            utils.saveResultString(this, fileName, saveKey, tempSave)
        }
        utils.goToAvtivity(this, MainActivity::class.java)
    }
}