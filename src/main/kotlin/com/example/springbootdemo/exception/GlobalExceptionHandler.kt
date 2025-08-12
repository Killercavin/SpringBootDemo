package com.example.springbootdemo.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


class EmptyMessagesException(message: String): RuntimeException(message)

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(EmptyMessagesException::class)
    fun handleEmptyMessages(e: EmptyMessagesException): String {
        return "${e.message}"
    }
}