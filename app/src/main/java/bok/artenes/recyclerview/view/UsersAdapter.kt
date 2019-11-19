package bok.artenes.recyclerview.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bok.artenes.recyclerview.viewmodel.MainActivityViewModel
import bok.artenes.recyclerview.viewmodel.UserItem

/**
 * An adapter to display a list of tasks.
 */
class UsersAdapter(
    private val viewModel: MainActivityViewModel,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<UsersAdapter.TaskViewHolder>() {

    private var list: List<UserItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = ListItem(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return TaskViewHolder(view)
    }

    fun submitList(newList: List<UserItem>) {
        val diffResult = DiffUtil.calculateDiff(
            UserDiff(
                list,
                newList
            )
        )
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }

        when (val payload = payloads.last()) {
            is CloseAnimationPayload -> {
                ListItemCloseAnimation(
                    holder.listItem,
                    payload.duration
                ).start()
            }
            is OpenAnimationPayload -> {
                ListItemOpenAnimation(
                    holder.listItem,
                    recyclerView,
                    position,
                    payload.duration
                ).also {
                    it.startOffset = payload.delay
                    it.start()
                }
            }
        }
    }

    private fun getItem(position: Int): UserItem {
        return list[position]
    }

    inner class TaskViewHolder(val listItem: ListItem) : RecyclerView.ViewHolder(listItem),
        View.OnClickListener {

        fun bind(position: Int) {
            val user = getItem(position)
            listItem.setName(user.name)
            listItem.setTitle(user.title)
            listItem.setDescription(user.description)
            listItem.setExpanded(user.expanded)
            listItem.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            viewModel.toggleUserDescription(getItem(adapterPosition))
        }

    }

    data class CloseAnimationPayload(val duration: Long)

    data class OpenAnimationPayload(val duration: Long, val delay: Long)

    class UserDiff(private val oldList: List<UserItem>, private val newList: List<UserItem>) :
        DiffUtil.Callback() {

        private val willCloseAnItemAndOpenAnother: Boolean

        init {

            val oldOpenIndex = oldList.indexOfFirst { it.expanded }
            val newOpenIndex = newList.indexOfFirst { it.expanded }
            willCloseAnItemAndOpenAnother =
                (oldOpenIndex >= 0 && newOpenIndex >= 0) && (oldOpenIndex != newOpenIndex)

        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val willClose = oldList[oldItemPosition].expanded && !newList[newItemPosition].expanded
            val willOpen = !oldList[oldItemPosition].expanded && newList[newItemPosition].expanded

            if (willClose) {
                return CloseAnimationPayload(ANIMATION_DURATION)
            }

            if (willOpen) {
                val delay = if (willCloseAnItemAndOpenAnother) ANIMATION_DURATION else 0
                return OpenAnimationPayload(
                    ANIMATION_DURATION,
                    delay
                )
            }

            return null
        }

        companion object {
            const val ANIMATION_DURATION = 250L
        }

    }

}

