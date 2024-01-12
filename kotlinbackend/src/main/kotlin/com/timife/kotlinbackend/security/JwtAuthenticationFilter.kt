package com.timife.kotlinbackend.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")
        if (authHeader.doesNotContainBearerToken()) {
            filterChain.doFilter(request, response)
            return
        }
        val jwtToken = authHeader!!.extractTokenValue()
        val userEmail = jwtService.extractUsername(jwtToken)

        if (userEmail != null && SecurityContextHolder.getContext().authentication == null) {
            val foundUser = userDetailsService.loadUserByUsername(userEmail)
            if (jwtService.isTokenValid(jwtToken, foundUser)) {
                updateContext(foundUser, request)
            }
        }
        filterChain.doFilter(request,response)
    }

    private fun updateContext(foundUser: UserDetails?, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(
            foundUser, null, foundUser?.authorities
        )
        authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authToken
    }

    fun String?.doesNotContainBearerToken(): Boolean {
        return this == null || !this.startsWith("Bearer ")
    }
}

private fun String.extractTokenValue(): String {
    return this.substringAfter("Bearer ")
}
