package com.example.data

import java.util.*

class User (
    val username: String,
    val email: String,
    val password: String
        ) {
    var id: String = UUID.randomUUID().toString().replace("-", "") //random object id

    override fun toString(): String {
        return username.toString()
    }
}