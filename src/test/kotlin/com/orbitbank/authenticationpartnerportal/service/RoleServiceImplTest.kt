package com.orbitbank.authenticationpartnerportal.service

import com.orbitbank.authenticationpartnerportal.dao.RoleDao
import com.orbitbank.authenticationpartnerportal.entity.Role
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class RoleServiceImplTest {

    val roleDao:RoleDao = mockk()
    val role = Role("Partner","Partner Role")
    val roleList = listOf(
        Role("Partner","Partner Role"),
        Role("Admin","Admin role")
    )

    val roleServiceImpl = RoleServiceImpl(roleDao)

    @Test
    fun createNewRole() {
        //given
        every { roleDao.save(role) } returns role

        //when
        val result = roleServiceImpl.createNewRole(role)

        //then
        verify(exactly = 1) { roleDao.save(role) }
        assertEquals(role,result)
    }

    @Test
    fun getAllRoles() {
        //given
        every { roleDao.findAll() } returns roleList

        //when
        val result = roleServiceImpl.getAllRoles()

        //then
        verify(exactly = 1) { roleDao.findAll() }
        assertEquals(roleList,result)
    }
}