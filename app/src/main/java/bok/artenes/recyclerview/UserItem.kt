package bok.artenes.recyclerview

data class UserItem(
    val name: String,
    val title: String,
    val description: String,
    val expanded: Boolean
) {

    companion object {

        fun from(user: User): UserItem {
            return UserItem(
                name = user.name,
                title = user.title,
                description = user.description,
                expanded = false
            )
        }

    }

}