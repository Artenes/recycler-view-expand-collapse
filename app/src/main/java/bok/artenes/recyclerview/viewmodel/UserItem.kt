package bok.artenes.recyclerview.viewmodel

import bok.artenes.recyclerview.model.User

data class UserItem(
    val id: String,
    val name: String,
    val title: String,
    val description: String,
    val expanded: Boolean
) {

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        val otherUser = other as UserItem
        return otherUser.id == id && otherUser.name == name && otherUser.title == title && otherUser.description == description
    }

    companion object {

        fun from(user: User, expanded: Boolean): UserItem {
            return UserItem(
                id = user.id,
                name = user.name,
                title = user.title,
                description = user.description,
                expanded = expanded
            )
        }

    }

}