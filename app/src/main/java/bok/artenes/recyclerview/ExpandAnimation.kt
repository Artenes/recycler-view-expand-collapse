package bok.artenes.recyclerview

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

class ExpandAnimation(private val view: View) : Animation() {

    private val realHeight: Int

    init {
        duration = 250
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        realHeight = view.measuredHeight
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        view.layoutParams.height = if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (realHeight * interpolatedTime).toInt()
        view.requestLayout()
    }

}