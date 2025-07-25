package com.lizgarciao.mymemorygame.models

enum class BoardSize(val numCards: Int) {
    EASY(8), // 2 x 2
    MEDIUM(18), //3 x 6
    HARD(24); // 4 x 6

    // Equivalent to number of columns
    fun getWidth(): Int {
        return when (this) {
            EASY -> 2
            MEDIUM -> 3
            HARD -> 4
        }
    }
    // Equivalent to number of rows (number of cards in each column)
    fun getHeight(): Int {
        return numCards / getWidth()
    }

    fun getNumPairs(): Int {
        return numCards / 2
    }
}