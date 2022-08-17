package com.orbitbank.authenticationpartnerportal.dao

import com.orbitbank.authenticationpartnerportal.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleDao: JpaRepository<Role, String> {
}