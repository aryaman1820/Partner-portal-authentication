package com.orbitbank.authenticationpartnerportal.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Role {

    @Id
    private var roleName: String
    private var roleDescription:String

    constructor(roleName:String, roleDescription:String){
        this.roleName = roleName
        this.roleDescription = roleDescription
    }

    fun getRoleName():String = roleName

    fun setRoleName(roleName:String){
        this.roleName = roleName
    }

    fun getRoleDescription():String = roleDescription

    fun setRoleDescription(roleDescription: String){
        this.roleDescription = roleDescription
    }

    override fun toString(): String {
        return "Role(roleName='$roleName', roleDescription='$roleDescription')"
    }


}