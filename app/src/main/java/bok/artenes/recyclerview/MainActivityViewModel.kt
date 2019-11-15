package bok.artenes.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val repository = UsersRepository()

    private val users: LiveData<List<UserItem>>

    init {
        users =
            Transformations.map(repository.getUsers()) { it.map { user -> UserItem.from(user) } }
    }

    fun getUsers(): LiveData<List<UserItem>> {
        return users
    }

}