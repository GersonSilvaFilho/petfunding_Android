package com.gersonsilvafilho.petfunding.main

import android.util.Log
import com.wenchao.cardstack.CardStack

/**
 * Created by GersonSilva on 5/2/17.
 */

class SwipeListener (val clickListener:mClickListener): CardStack.CardEventListener{


    //implement card event interface
    override fun swipeEnd(direction: Int, distance: Float): Boolean {
        //if "return true" the dismiss animation will be triggered
        //if false, the card will move back to stack
        //distance is finger swipe distance in dp

        //the direction indicate swipe direction
        //there are four directions
        //  0  |  1
        // ----------
        //  2  |  3

        return if (distance > 300) true else false
    }

    override fun swipeStart(direction: Int, distance: Float): Boolean {

        return true
    }

    override fun swipeContinue(direction: Int, distanceX: Float, distanceY: Float): Boolean {



        return true
    }

    override fun discarded(id: Int, direction: Int) {
        Log.d("TESTE", "Valores:" + id + " " + direction)
        clickListener.cardWasDiscarted(id - 1)
        //this callback invoked when dismiss animation is finished.
    }

    override fun topCardTapped()
    {
        clickListener.mClick()
        //this callback invoked when a top card is tapped by user.
    }

    interface mClickListener {
        fun mClick()
        fun cardWasDiscarted(cardId:Int)
    }
}