package com.merpyzf.timer

import android.os.Handler
import android.os.Looper
import android.os.SystemClock


/**
 *
 * @author: WK
 * @date: 2023/5/29
 */
class CountUpTimer : Timer() {
    private val tickListeners = mutableListOf<TickListener>()
    private val handler = Handler(Looper.getMainLooper())

    private var currentStartTime = 0L
    private var baseElapsedTime = 0L
    private var currentElapsedTime = 0L

    override val currentTime: Long
        get() = baseElapsedTime + currentElapsedTime


    override fun start() {
        super.start()
        currentStartTime = SystemClock.elapsedRealtime()
        currentElapsedTime = 0
        handler.post(TickRunnable())
    }

    override fun pause() {
        super.pause()
        handler.removeCallbacksAndMessages(null)
        baseElapsedTime += currentElapsedTime
        currentElapsedTime = 0
    }

    override fun forceStop() {
        super.forceStop()
        handler.removeCallbacksAndMessages(null)
    }

    private fun onTick(newElapsedTime: Long) {
        currentElapsedTime = newElapsedTime
        val time = currentTime
        tickListeners.forEach {
            it.onNewTime(time)
        }
    }

    fun addTickListener(listener: TickListener) {
        tickListeners.add(listener)
    }

    private inner class TickRunnable : Runnable {
        override fun run() {
            val tickStartTime = SystemClock.elapsedRealtime()

            val newElapsedTime = tickStartTime - currentStartTime
            onTick(newElapsedTime)

            val tickEndTime = SystemClock.elapsedRealtime()
            val consume = tickEndTime - tickStartTime
            handler.postDelayed(this, 1_000L - (consume % 1_1000))
        }
    }
}