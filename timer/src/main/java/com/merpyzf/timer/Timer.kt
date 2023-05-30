package com.merpyzf.timer

import androidx.annotation.CallSuper

/**
 *
 * @author: WK
 * @date: 2023/5/13
 */
abstract class Timer {
    var timerState: TimerState = TimerState.RESET
        internal set
    open val currentTime : Long = 0L

    fun isRunning(): Boolean{
        return timerState.isRunning
    }

    @CallSuper
    open fun start() {
        timerState = TimerState.RUNNING
    }

    @CallSuper
    open fun pause() {
        timerState = TimerState.PAUSED
    }

    @CallSuper
    open fun forceStop() {
        timerState = TimerState.RESET
    }
}