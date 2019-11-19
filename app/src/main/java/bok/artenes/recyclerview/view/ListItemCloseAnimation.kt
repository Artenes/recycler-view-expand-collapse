package bok.artenes.recyclerview.view

import android.animation.ValueAnimator

class ListItemCloseAnimation(private val listItem: ListItem, private val duration: Long) {

    fun start() {
        listItem.arrowView.animate().setDuration(duration).rotation(0F)
        val closeAnimation = ValueAnimator.ofInt(listItem.descriptionView.measuredHeight, 0)
        closeAnimation.duration = duration
        closeAnimation.addUpdateListener { animation ->
            listItem.descriptionView.layoutParams.height = animation.animatedValue as Int
            listItem.descriptionView.requestLayout()
        }
        closeAnimation.start()
    }

}