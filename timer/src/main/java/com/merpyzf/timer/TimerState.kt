package com.merpyzf.timer

/**
 * 计时器状态
 * @author: WK
 * @date: 2023/5/13
 */
enum class TimerState {
    RUNNING, PAUSED, RESET;

    val isRunning get() = this == RUNNING
    val isPaused get() = this == PAUSED
    val isReset get() = this == RESET
}