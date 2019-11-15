package bok.artenes.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class UsersRepository {

    fun getUsers(): LiveData<List<User>> {
        return MutableLiveData<List<User>>(
            listOf(
                User(
                    name = "Jhon Doe",
                    title = "Software Engineer",
                    description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in"
                ),
                User(
                    name = "Mary Doe",
                    title = "House Engineer",
                    description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in"
                ),
                User(
                    name = "Tom Doe",
                    title = "Car Engineer",
                    description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in"
                ),
                User(
                    name = "Jeniffer Doe",
                    title = "Table Engineer",
                    description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in"
                ),
                User(
                    name = "Louren Doe",
                    title = "Sound Engineer",
                    description = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in"
                )
            )
        )
    }

}