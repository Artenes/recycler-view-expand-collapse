package bok.artenes.recyclerview

import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView

class UserListToggleAnimator(private val recyclerView: RecyclerView) {

    fun animate(payload: ToggleAnimationPayload) {
        val closeAnimation = makeCloseAnimation(payload.toClose)
        val openAnimation = makeOpenAnimation(payload.toOpen)

        if (closeAnimation != null && openAnimation != null) {
            openAnimation.startOffset = 250
        }

        closeAnimation?.start()
        openAnimation?.start()
    }

    private fun makeCloseAnimation(position: Int): Animation? {
        val toCloseView = getListItem(position) ?: return null
        return ListItemCloseAnimation(toCloseView, 250)
    }

    private fun makeOpenAnimation(position: Int): Animation? {
        val toOpenView = getListItem(position) ?: return null
        return ListItemOpenAnimation(toOpenView, recyclerView, position, 250)
    }

    private fun getListItem(position: Int): ListItem? {
        return ((recyclerView.findViewHolderForAdapterPosition(position) as UsersAdapter.TaskViewHolder?)?.itemView as ListItem?)
    }

}