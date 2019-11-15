package bok.artenes.recyclerview

import java.util.*

data class User(val id: String = UUID.randomUUID().toString(), val name: String, val title: String, val description: String)