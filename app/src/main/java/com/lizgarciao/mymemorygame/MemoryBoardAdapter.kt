package com.lizgarciao.mymemorygame

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lizgarciao.mymemorygame.models.BoardSize
import com.lizgarciao.mymemorygame.models.MemoryCard
import kotlin.math.min

/** MemoryBoardAdapter.ViewHolder: This is the type argument being passed to the generic RecyclerView.Adapter class. It specifies that this particular adapter (your MemoryBoardAdapter) will work with ViewHolder objects of the type MemoryBoardAdapter.ViewHolder. */
class MemoryBoardAdapter(
    private val context: Context,
    private val boardSize: BoardSize,
    private val cards: List<MemoryCard>,
    private val cardClickListener: CardClickListener
) :
    RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {

    /** Define members (properties and functions) that are STATIC (tied to the class itself, rather than to instances of the class) */
    companion object {
        private const val MARGIN_SIZE = 10
        private const val TAG = "MemoryBoardAdapter"
    }

    interface CardClickListener {
        fun onCardClicked(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)
        fun bind(position: Int) {
            val memoryCard = if (cards[position].isFaceUp) cards[position].identifier else R.drawable.ic_launcher_background
            imageButton.setImageResource(memoryCard)
            imageButton.setOnClickListener {
                Log.i(TAG, "Clicked on position $position")
                cardClickListener.onCardClicked(position)
            }
        }
    }

    /** Implement methods of RecyclerView.Adapter which is an abstract class */

    // Responsible for creating a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardWidth: Int = parent.width / boardSize.getWidth()
        val cardHeight: Int = parent.height / boardSize.getHeight()
        val cardSideLength: Int = min(cardWidth, cardHeight) - (2 * MARGIN_SIZE)
        val view: View = LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)
        val layoutParams =
            view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = cardSideLength
        layoutParams.height = cardSideLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(view)
    }

    // Responsible for determining how many items are in the RecyclerView
    override fun getItemCount() = boardSize.numCards

    // Responsible for taking a data item and binding it to a ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

}
