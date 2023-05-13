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

    /**
     * @param add True to add amount to the current time. False to set current time to amount
     */
    abstract fun adjust(amount: Long, add: Boolean)
}