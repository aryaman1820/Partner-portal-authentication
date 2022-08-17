package com.orbitbank.authenticationpartnerportal.entity


import javax.persistence.*

@Entity
class User {

    @Id
    private lateinit var userName:String
    private lateinit var userEmail:String
    private lateinit var userPassword:String

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(name = "USER_ROLE",
        joinColumns = [
            JoinColumn(name = "USER_ID")
        ], inverseJoinColumns = [
            JoinColumn(name = "ROLE_ID")
        ]
        )
    private lateinit var role:Set<Role>

    private var isVerified:String = "Not Verified"

    constructor(userName:String, userEmail:String, userPassword:String, role: Set<Role>, isVerified: String){
        this.userName = userName
        this.userEmail = userEmail
        this.userPassword = userPassword
        this.role = role
        this.isVerified = isVerified
    }

    fun getUserName():String = userName

    fun setUserName(userName: String){
        this.userName = userName
    }
    fun getUserEmail(): String = userEmail

    fun setUserEmail(userEmail: String) {
        this.userEmail = userEmail
    }
    fun getUserPassword(): String = userPassword

    fun setUserPassword(password: String) {
        userPassword = password
    }

    fun getRole(): Set<Role?> = role

    fun setRole(role: Set<Role>) {
        this.role = role
    }

    fun getIsVerified(): String = isVerified

    fun setIsVerified(isVerified: String?) {
        this.isVerified = isVerified!!
    }

    override fun toString(): String {
        return "User(userName='$userName', userEmail='$userEmail', userPassword='$userPassword', role=$role, isVerified='$isVerified')"
    }


}