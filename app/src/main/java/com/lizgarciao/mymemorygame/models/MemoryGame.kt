package com.lizgarciao.mymemorygame.models

import com.lizgarciao.mymemorygame.utils.DEFAULT_ICONS

/** It is this class' responsibility to create all the cards (should not happen in MainActivity.kt) */
class MemoryGame(private val boardSize: BoardSize) {
    val cards: List<MemoryCard>
    var numPairsFound = 0

    private var indexOfsingleSelectedCard : Int? = null

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        // 'it' represents each individual element in the list randomizedImages cast to a MemoryCard
        cards = randomizedImages.map { MemoryCard(it) }
    }

    fun flipCard(position: Int): Boolean {
        var foundMatch = false
        // Three cases (which can be shortened to two: 1 and 3 can be the same operation)
        // 0 cards previously flipped over => (restore cards + ) flip over the selected card
        // 1 card previously flipped over => flip over the selected card + check if the images match
        // 2 cards previously flipped over => restore cards + flip over the selected card
        val card = cards[position]
        if (indexOfsingleSelectedCard == null) { // cases 0 and 2
            restoreCards()
            indexOfsingleSelectedCard = position
        } else { // single card previously flipped over
            foundMatch = checkForMatch(indexOfsingleSelectedCard!!, position)
            indexOfsingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    fun hasWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    private fun restoreCards() {
        for (card in cards) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier != cards[position2].identifier) {
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }
}