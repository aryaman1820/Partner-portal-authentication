package com.orbitbank.authenticationpartnerportal.service

import com.orbitbank.authenticationpartnerportal.dao.RoleDao
import com.orbitbank.authenticationpartnerportal.dao.UserDao
import com.orbitbank.authenticationpartnerportal.entity.Role
import com.orbitbank.authenticationpartnerportal.entity.User
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.security.crypto.password.PasswordEncoder

internal class UserServiceImplTest {

    val userDao:UserDao = mockk()
    val roleDao:RoleDao = mockk()
    val passwordEncoder:PasswordEncoder = mockk()
    val userServiceImpl = UserServiceImpl(userDao,roleDao, passwordEncoder)
    val role = Role("Partner","Partner Role")
    val user = User ("Test","test@test.com","\$2a\$12\$.8Ysvi99ZRRpR.6cBgxOJOZsqO4C4urmW6Cb55zI9H/Gn.t9DG6uS",
        setOf(Role("Partner","Partner role")),"Not Verified")
    val password:String = "\$2a\$12\$.8Ysvi99ZRRpR.6cBgxOJOZsqO4C4urmW6Cb55zI9H/Gn.t9DG6uS"

    val userList = listOf(User("User1","user1@gmail.com","\$2a\$12\$.8Ysvi99ZRRpR.6cBgxOJOZsqO4C4urmW6Cb55zI9H/Gn.t9DG6uS",
        setOf(role),"Not Verified"),User("User2","user2@gmail.com","\$2a\$12\$.8Ysvi99ZRRpR.6cBgxOJOZsqO4C4urmW6Cb55zI9H/Gn.t9DG6uS",
        setOf(role),"Not Verified"))

    /*@Test
    fun registerNewUser(){
        //given
        every { userDao.save(user) } returns user

        //when
        val result = userServiceImpl.registerNewUser(user)

        //then
        verify(exactly = 1) { userDao.save(user) }
        assertEquals(user,result)
    }*/


    @Test
    fun getEncodedPassword(){
        //given
        every { passwordEncoder.encode("test") } returns password

        //when
        val result = userServiceImpl.getEncodedPassword("test")

        //then
        verify(exactly = 1) { passwordEncoder.encode("test") }
        assertEquals(password,result)
    }



    @Test
    fun findByUserName() {

        //given
        every { userDao.findById("Test").get() } returns user

        //when
        val result = userServiceImpl.findByUserName("Test")

        //then
        verify(exactly = 1) { userDao.findById("Test").get() }
        assertEquals(user,result)
    }

    @Test
    fun listAll(){
        //given
        every { userDao.findAll() } returns userList

        //when
        val result = userServiceImpl.listAll()

        //then
        verify(exactly = 1) { userDao.findAll() }
        assertEquals(userList,result)
    }

}