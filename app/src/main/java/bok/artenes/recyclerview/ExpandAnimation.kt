package bok.artenes.recyclerview

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class ExpandAnimation(private val view: View) : Animation() {

    private val realHeight: Int

    init {
        duration = 250
        //the max width is necessary so it can calculates how height the view is when we give UNSPECIFIED
        val width = View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
        val height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        view.measure(width, height)
        realHeight = view.measuredHeight
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        view.layoutParams.height = (realHeight * interpolatedTime).toInt()
        view.requestLayout()
    }

}