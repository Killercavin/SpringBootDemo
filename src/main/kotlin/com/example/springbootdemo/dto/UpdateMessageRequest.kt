package com.example.springbootdemo.dto

import jakarta.validation.constraints.NotBlank

data class UpdateMessageRequest(
    @field:NotBlank(message = "Text is required")
    var text: String
)
