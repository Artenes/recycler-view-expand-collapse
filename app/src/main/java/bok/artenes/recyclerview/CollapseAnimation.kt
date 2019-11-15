package bok.artenes.recyclerview

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class CollapseAnimation(private val view: View) : Animation() {

    init {
        duration = 250
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        view.layoutParams.height =
            view.measuredHeight - (view.measuredHeight * interpolatedTime).toInt()
        view.requestLayout()
    }

}