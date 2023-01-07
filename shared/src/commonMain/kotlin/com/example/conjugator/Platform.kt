package com.example.conjugator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform