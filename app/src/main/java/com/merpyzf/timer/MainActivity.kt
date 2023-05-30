package com.merpyzf.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.merpyzf.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val countUpTimer: CountUpTimer by lazy {
        CountUpTimer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateToggleText()
        initEvent()
    }

    private fun initEvent() {
        countUpTimer.addTickListener(object : TickListener {
            override fun onNewTime(newTime: Long) {
                Log.i("wk", "newTime: $newTime")
                binding.tvElapsedTime.text = "$newTime"
            }
        })
        binding.btnTimerToggle.setOnClickListener {
            if (countUpTimer.isRunning()) {
                countUpTimer.pause()
            } else {
                countUpTimer.start()
            }
            updateToggleText()
        }
    }

    private fun updateToggleText() {
        binding.btnTimerToggle.text = if (countUpTimer.isRunning()) {
            "暂停"
        } else {
            "开始"
        }
    }
}