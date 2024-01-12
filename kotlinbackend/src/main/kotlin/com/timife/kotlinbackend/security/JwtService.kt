package com.timife.kotlinbackend.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import javax.crypto.SecretKey
import kotlin.collections.HashMap
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
class JwtService {
    private val secretKey =
        "0G6NqCE3pycGnCAY6ixe7VYgy2LMoT9uZ0hlTT/TdFFZDOiOefY5rFHFi3Ma3FL9zzjffYR5wYF6rUfAsUa6I4jYwKUd+NjW1tVUomIxKYBF9ivkDP9qV43roGjDre3uI9aOo+WmBwOlmwlozurBwdqvH0qkb5OOQ4d88RVgKAK0G015zUDekHfdH8//5Rq4GM97BO21UOWiWFEcay5VehvXB8lZ7CorVQawOHouDqW5QCO/nYMMC/cEgtgo8g+JCaXVB48C9kA4vbkIAGakZC5kb9Ys+B2d8trursWiZfYAy4kY85fDqS8x/Qx38ouYfOre1D2WRZkb9uhafbUqPIJHeB7FF4lYZ81HlUq+/Pw="


    fun extractUsername(token: String): String? {
        return extractClaim(token, Claims::getSubject)
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver.invoke(claims)
    }

    private fun generateToken(extraClaims: Map<String, JvmType.Object>, userDetails: UserDetails): String {
        return Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .add(extraClaims)
            .and()
            .signWith(getSignInKey())
            .compact()
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(HashMap(), userDetails)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).payload
    }

    private fun getSignInKey(): SecretKey {
        val bytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(bytes)
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val userName = extractUsername(token)
        return userName == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }
}