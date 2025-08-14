package com.example.springbootdemo.dto

import jakarta.validation.constraints.NotBlank

data class CreateMessageRequest(
    @field:NotBlank(message = "Text cannot be blank")
    val text: String,

    @field:NotBlank(message = "Author cannot be blank")
    val author: String
)
