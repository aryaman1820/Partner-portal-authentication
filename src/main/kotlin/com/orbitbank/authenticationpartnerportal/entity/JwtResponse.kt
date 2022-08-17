package com.orbitbank.authenticationpartnerportal.entity

class JwtResponse {

    private var user: User
    private var jwtToken : String

    constructor(user:User, jwtToken:String ){
        this.user = user
        this.jwtToken = jwtToken
    }

    fun getUser():User = user

    fun setUser(user:User){
        this.user = user
    }

    fun getJwtToken():String = jwtToken

}