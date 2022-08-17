package com.orbitbank.authenticationpartnerportal.service

import com.orbitbank.authenticationpartnerportal.entity.User


interface UserService {

    fun registerNewUser(user: User): User

    fun getEncodedPassword(password : String):String

    fun listAll():List<User>

    fun findByUserName(userName :String): User

    fun verifyUser(userName: String)
}