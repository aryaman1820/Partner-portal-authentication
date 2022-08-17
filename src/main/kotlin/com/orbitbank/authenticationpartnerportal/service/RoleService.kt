package com.orbitbank.authenticationpartnerportal.service

import com.orbitbank.authenticationpartnerportal.entity.Role

interface RoleService {

    fun createNewRole(role: Role): Role

    fun getAllRoles(): List<Role>
}