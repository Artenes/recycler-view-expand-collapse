package bok.artenes.recyclerview

data class UserItem(
    val id: String,
    val name: String,
    val title: String,
    val description: String,
    val expanded: Boolean
) {

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