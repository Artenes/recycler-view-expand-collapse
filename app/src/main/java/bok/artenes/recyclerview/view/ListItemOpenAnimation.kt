package bok.artenes.recyclerview.view

import android.animation.ValueAnimator
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListItemOpenAnimation(
    private val listItem: ListItem,
    private val recyclerView: RecyclerView,
    private val targetPosition: Int,
    private val duration: Long,
    private val delay: Long
) {

    private val realHeight: Int

    init {
        //the max width is necessary so it can calculates how height the view is when we give UNSPECIFIED
        val view = listItem.descriptionView
        val width =
            View.MeasureSpec.makeMeasureSpec((view.parent as View).width, View.MeasureSpec.EXACTLY)
        val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(width, height)
        realHeight = view.measuredHeight
        view.layoutParams.height = 0
        view.visibility = View.VISIBLE
    }

    fun start() {
        listItem.arrowView.animate().setDuration(duration).rotation(180F)
        val openAnimation = ValueAnimator.ofInt(0, realHeight)
        openAnimation.duration = duration
        openAnimation.startDelay = delay
        openAnimation.addUpdateListener { animation ->
            listItem.descriptionView.layoutParams.height = animation.animatedValue as Int
            listItem.descriptionView.requestLayout()
            recyclerView.smoothScrollToPosition(targetPosition)
        }
        openAnimation.start()
    }

}