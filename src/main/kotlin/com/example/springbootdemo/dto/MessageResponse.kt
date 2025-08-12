package com.example.springbootdemo.dto

import java.time.LocalDateTime

data class MessageResponse(
    val id: Long,
    val author: String,
    val text: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
