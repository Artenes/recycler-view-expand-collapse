package bok.artenes.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val repository = UsersRepository()

    private val users: MutableLiveData<List<UserItem>> = MutableLiveData(emptyList())
    //FIXME this should be consumed only once after being emitted
    private val animateToggle: MutableLiveData<ToggleAnimationPayload> = MutableLiveData()

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

    fun getAnimateToggle(): LiveData<ToggleAnimationPayload> {
        return animateToggle
    }

    fun toggleUserDescription(user: UserItem) {
        val users = users.value?.toMutableList() ?: mutableListOf()

        val toClose: Int
        var toOpen: Int = -1
        val isGoingToOpen = !user.expanded

        toClose = users.indexOfFirst { it.expanded }
        if (toClose > -1) {
            users[toClose] = users[toClose].copy(expanded = false)
        }

        if (isGoingToOpen) {
            toOpen = users.indexOf(user)
            if (toOpen > -1) {
                users[toOpen] = user.copy(expanded = true)
            }
        }

        animateToggle.value = ToggleAnimationPayload(toClose, toOpen)
        this.users.value = users
    }

}