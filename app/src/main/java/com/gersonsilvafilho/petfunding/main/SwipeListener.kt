package com.gersonsilvafilho.petfunding.main

import android.util.Log
import com.wenchao.cardstack.CardStack

/**
 * Created by GersonSilva on 5/2/17.
 */

class SwipeListener(private val clickListener: mClickListener) : CardStack.CardEventListener {
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

        return distance > 300
    }

    override fun swipeStart(direction: Int, distance: Float): Boolean {
        return true
    }

    override fun swipeContinue(direction: Int, distanceX: Float, distanceY: Float): Boolean {
        return true
    }

    override fun discarded(id: Int, direction: Int) {
        Log.d("TESTE", "Valores:" + id + " " + direction)
        if(direction % 2 == 0)
        {
            clickListener.cardDiscartedLeft(id - 1)
        }
        else
        {
            //Discarted to right
            clickListener.cardDiscartedRight(id - 1)
        }

        //this callback invoked when dismiss animation is finished.
    }

    override fun topCardTapped()
    {
        clickListener.mClick()
        //this callback invoked when a top card is tapped by user.
    }

    interface mClickListener {
        fun mClick()
        fun cardDiscartedRight(cardId:Int)
        fun cardDiscartedLeft(cardId:Int)
    }
}