package bok.artenes.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val repository = UsersRepository()

    private val users: MutableLiveData<List<UserItem>> = MutableLiveData(emptyList())

    private val usersObserver = Observer<List<User>> {
        val userItemList = users.value ?: emptyList()
        val users = it.map { user ->
            val userItem = userItemList.find { item -> item.id == user.id }
            UserItem.from(user, userItem?.expanded ?: false)
        }
        this.users.value = users
    }

    init {
        repository.getUsers().observeForever(usersObserver)
    }

    fun getUsers(): LiveData<List<UserItem>> {
        return users
    }

    fun toggleUserDescription(user: UserItem) {
        val users = users.value?.toMutableList() ?: mutableListOf()

        val isGoingToExpand = !user.expanded

        val expandedIndex = users.indexOfFirst { it.expanded }
        if (expandedIndex > -1) {
            users[expandedIndex] = users[expandedIndex].copy(expanded = false)
        }

        if (isGoingToExpand) {
            val index = users.indexOf(user)
            if (index > -1) {
                users[index] = user.copy(expanded = true)
            }
        }

        this.users.value = users
    }

}