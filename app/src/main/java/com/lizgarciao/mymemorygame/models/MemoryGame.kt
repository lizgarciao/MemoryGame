package com.lizgarciao.mymemorygame.models

import com.lizgarciao.mymemorygame.utils.DEFAULT_ICONS

/** Delegate's responsibility of creating all the cards into this class (should not happen in MainActivity.kt) */
class MemoryGame(private val boardSize: BoardSize) {
    val cards: List<MemoryCard>
    val numPairsFound = 0

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        cards = randomizedImages.map { MemoryCard(it) }
    }
}