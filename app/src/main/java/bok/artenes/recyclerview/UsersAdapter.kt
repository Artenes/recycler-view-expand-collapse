package bok.artenes.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * An adapter to display a list of tasks.
 */
class UsersAdapter(val viewModel: MainActivityViewModel) : ListAdapter<UserItem, UsersAdapter.TaskViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = ListItem(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class TaskViewHolder(private val listItem: ListItem) : RecyclerView.ViewHolder(listItem),  View.OnClickListener {

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

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>() {

            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }
        }

    }

}

