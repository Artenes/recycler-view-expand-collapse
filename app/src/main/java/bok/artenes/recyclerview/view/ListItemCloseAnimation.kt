package bok.artenes.recyclerview.view

import android.view.animation.Animation
import android.view.animation.Transformation

class ListItemCloseAnimation(private val listItem: ListItem, duration: Long) : Animation() {

    init {
        this.duration = duration
    }

    override fun start() {
        super.start()
        listItem.getArrowView().animate().setDuration(duration).rotation(0F)
        listItem.getDescriptionView().startAnimation(this)
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val view = listItem.getDescriptionView()
        view.layoutParams.height =
            view.measuredHeight - (view.measuredHeight * interpolatedTime).toInt()
        view.requestLayout()
    }

}