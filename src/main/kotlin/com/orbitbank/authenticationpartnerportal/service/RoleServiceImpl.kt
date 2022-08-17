package com.orbitbank.authenticationpartnerportal.service

import com.orbitbank.authenticationpartnerportal.dao.RoleDao
import com.orbitbank.authenticationpartnerportal.entity.Role
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleDao: RoleDao):RoleService {

    override fun createNewRole(role: Role): Role {
        return roleDao.save(role)
    }

    override fun getAllRoles(): List<Role> {
        return roleDao.findAll()
    }
}