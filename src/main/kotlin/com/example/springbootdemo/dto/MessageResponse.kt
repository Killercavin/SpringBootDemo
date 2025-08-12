package com.example.springbootdemo.dto

import com.example.springbootdemo.entity.Message
import java.time.LocalDateTime

data class MessageResponseDTO(
    val id: Long,
    val text: String,
    val author: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)

fun Message.toDTO(): MessageResponseDTO {
    return MessageResponseDTO(
        id = this.id,
        text = this.text,
        author = this.author,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}
