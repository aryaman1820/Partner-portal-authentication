package com.orbitbank.authenticationpartnerportal.service

import com.orbitbank.authenticationpartnerportal.dao.UserDao
import com.orbitbank.authenticationpartnerportal.entity.JwtRequest
import com.orbitbank.authenticationpartnerportal.entity.JwtResponse
import com.orbitbank.authenticationpartnerportal.entity.User
import com.orbitbank.authenticationpartnerportal.util.JwtUtil
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtService(private val userDao: UserDao, private val jwtUtil: JwtUtil,@Lazy private val authenticationManager: AuthenticationManager): UserDetailsService {


	@Throws(java.lang.Exception::class)
	fun createJwtToken(jwtRequest: JwtRequest): JwtResponse {
		val userName: String = jwtRequest.getUserName()
		val userPassword: String = jwtRequest.getUserPassword()
		authenticate(userName, userPassword)
		val userDetails = loadUserByUsername(userName)
		val newGeneratedToken: String = jwtUtil.generateToken(userDetails)
		val user = userDao.findById(userName).get()
		return JwtResponse(user, newGeneratedToken)
	}

	override fun loadUserByUsername(username: String): UserDetails {
        val user:User = userDao.findById(username).get()
		return if (user != null) {
			org.springframework.security.core.userdetails.User(
				user.getUserName(),
				user.getUserPassword(),
				getAuthority(user)
			)
		} else {
			throw UsernameNotFoundException("Username not valid")
		}
    }

	private fun getAuthority(user: User): MutableCollection<out GrantedAuthority> {
		val authorities: MutableSet<SimpleGrantedAuthority> = HashSet()
		user.getRole().forEach { role ->
			if (role != null) {
				authorities.add(SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
			}
		}
		return authorities
	}

	@Throws(Exception::class)
	private fun authenticate(userName: String, userPassword: String) {
		try {
			authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userName, userPassword))
		} catch (e: DisabledException) {
			throw Exception("User is disabled")
		} catch (e: BadCredentialsException) {
			throw Exception("Bad Credentials from User")
		}
	}
}