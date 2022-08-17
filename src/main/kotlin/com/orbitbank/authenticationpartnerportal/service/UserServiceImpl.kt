package com.orbitbank.authenticationpartnerportal.service

import com.orbitbank.authenticationpartnerportal.dao.RoleDao
import com.orbitbank.authenticationpartnerportal.dao.UserDao
import com.orbitbank.authenticationpartnerportal.entity.Role
import com.orbitbank.authenticationpartnerportal.entity.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(private val userDao:UserDao, private val roleDao:RoleDao, private val passwordEncoder:PasswordEncoder): UserService {

    override fun registerNewUser(user: User): User {
        val role : Role = roleDao.findById("Partner").get()
        val userRoles: MutableSet<Role> = HashSet()
        userRoles.add(role)
        user.setRole(userRoles)
        user.setUserPassword(getEncodedPassword(user.getUserPassword()))

        return userDao.save(user)

    }

    override fun getEncodedPassword(password: String): String {
        return passwordEncoder.encode(password)
    }

    override fun listAll(): List<User> {
        return userDao.findAll()
    }

    override fun findByUserName(userName: String): User {
        return userDao.findById(userName).get()
    }

    override fun verifyUser(userName: String) {
        val user = userDao.findById(userName).get()
        user.setIsVerified("Verified")
        userDao.save(user)
    }
}