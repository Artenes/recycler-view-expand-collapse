package bok.artenes.recyclerview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import bok.artenes.recyclerview.model.UsersRepository
import bok.artenes.recyclerview.model.User

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

        val isGoingToOpen = !user.expanded

        val closeIndex = users.indexOfFirst { it.expanded }
        if (closeIndex > -1) {
            users[closeIndex] = users[closeIndex].copy(expanded = false)
        }

        if (isGoingToOpen) {
            val openIndex = users.indexOf(user)
            if (openIndex > -1) {
                users[openIndex] = user.copy(expanded = true)
            }
        }

        this.users.value = users
    }

}