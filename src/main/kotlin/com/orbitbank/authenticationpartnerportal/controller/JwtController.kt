package com.orbitbank.authenticationpartnerportal.controller

import com.orbitbank.authenticationpartnerportal.entity.JwtRequest
import com.orbitbank.authenticationpartnerportal.entity.JwtResponse
import com.orbitbank.authenticationpartnerportal.service.JwtService

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin
class JwtController(private val jwtService: JwtService) {

    @PostMapping("/login")
    @Throws(Exception::class)
    fun createJwtToken(@RequestBody jwtRequest: JwtRequest): JwtResponse {
        return jwtService.createJwtToken(jwtRequest)
    }
}
