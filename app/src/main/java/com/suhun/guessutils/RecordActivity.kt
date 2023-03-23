package com.suhun.guessutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suhun.guessutils.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding
    val tag:String = RecordActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}