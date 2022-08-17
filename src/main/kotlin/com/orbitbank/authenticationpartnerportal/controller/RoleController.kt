package com.orbitbank.authenticationpartnerportal.controller

import com.orbitbank.authenticationpartnerportal.entity.Role
import com.orbitbank.authenticationpartnerportal.service.RoleService
import com.orbitbank.authenticationpartnerportal.service.RoleServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController("/api/role")
class RoleController(private val roleServiceImpl: RoleServiceImpl) {

    @PostMapping("/createNewRole")
    @PreAuthorize("hasRole('Admin')")
    fun createNewRole(@RequestBody role: Role): ResponseEntity<*> {
        roleServiceImpl.createNewRole(role)
        return ResponseEntity.ok("Role Created Successfully")
    }

    @GetMapping("/get/roles")
    fun getAllRoles():List<Role>{
        return roleServiceImpl.getAllRoles()
    }
}
