package com.example.sankofa.Model

data class Player(
    val id: String = "",
    val name: String = "",
    val isDone: Boolean = false,
    val createdAt: Long = 0L
)
