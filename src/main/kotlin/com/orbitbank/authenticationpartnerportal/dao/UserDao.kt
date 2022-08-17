package com.orbitbank.authenticationpartnerportal.dao

import com.orbitbank.authenticationpartnerportal.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDao: JpaRepository<User, String> {
}