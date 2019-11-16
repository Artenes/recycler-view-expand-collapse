package bok.artenes.recyclerview

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.recyclerview.widget.RecyclerView

class ListItemOpenAnimation(
    private val listItem: ListItem,
    private val recyclerView: RecyclerView,
    private val targetPosition: Int,
    duration: Long
) : Animation() {

    private val realHeight: Int

    init {
        this.duration = duration
        //the max width is necessary so it can calculates how height the view is when we give UNSPECIFIED
        val view = listItem.getDescriptionView()
        val width = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
        val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(width, height)
        realHeight = view.measuredHeight
        view.layoutParams.height = 0
        view.visibility = View.VISIBLE
    }

    override fun start() {
        super.start()
        listItem.getArrowView().animate().setDuration(duration).rotation(180F)
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val view = listItem.getDescriptionView()
        view.layoutParams.height = (realHeight * interpolatedTime).toInt()
        view.requestLayout()
        recyclerView.smoothScrollToPosition(targetPosition)
    }

}