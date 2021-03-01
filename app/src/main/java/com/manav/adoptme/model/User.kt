package com.manav.adoptme.model

import com.manav.adoptme.R

data class User(
    val firstName: String,
    val lastName: String,
    val photo: Int,
    val fullName: String = "$firstName $lastName",
    val email: String
) {
    companion object
}

val User.Companion.fake: User
    get() = User(
        firstName = "Manav", lastName = "Shah",
        photo = R.drawable.manav, email = "manav.march12@gmail.com"
    )

