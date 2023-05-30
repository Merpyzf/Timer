package com.merpyzf.timer

/**
 *
 * @author: WK
 * @date: 2023/5/30
 */
interface TickListener {
    fun onNewTime(newTime: Long)
}