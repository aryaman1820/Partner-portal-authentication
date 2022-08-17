package com.orbitbank.authenticationpartnerportal.config

import com.orbitbank.authenticationpartnerportal.service.JwtService
import com.orbitbank.authenticationpartnerportal.util.JwtUtil
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtRequestFilter(private val jwtUtil: JwtUtil, @Lazy private val jwtService: JwtService) : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")
        var jwtToken: String? = null
        var userName: String? = null
        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7)
            try {
                userName = jwtUtil.getUserNameFromToken(jwtToken)
            } catch (e: IllegalArgumentException) {
                println("Unable to get the JWT Token")
            } catch (e: ExpiredJwtException) {
                println("JWT Token is expired")
            }
        } else {
            println("JWT token does not start from bearer")
        }
        if (userName != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = jwtService.loadUserByUsername(userName)
            if (jwtUtil.validateToken(jwtToken!!, userDetails)) {
                val usernamePasswordAuthenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            }
        }
        filterChain.doFilter(request, response)
    }
}
