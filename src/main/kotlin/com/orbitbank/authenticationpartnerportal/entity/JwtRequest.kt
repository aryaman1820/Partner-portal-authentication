package com.orbitbank.authenticationpartnerportal.entity

class JwtRequest {

    private lateinit var userName:String
    private lateinit var  userPassword: String

    fun getUserName(): String = userName

    fun setUserName(userName : String){
        this.userName = userName
    }

    fun getUserPassword(): String = userPassword

    fun setUserPassword(userPassword : String){
        this.userPassword = userPassword
    }
}