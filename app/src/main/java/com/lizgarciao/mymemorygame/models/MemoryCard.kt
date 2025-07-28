package com.lizgarciao.mymemorygame.models

/** Lists out all attributes of each memory card */
data class MemoryCard(
    val identifier: Int,
    var isFaceUp: Boolean = false,
    var isMatched: Boolean = false
)