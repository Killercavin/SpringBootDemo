package com.example.springbootdemo.controllers

import com.example.springbootdemo.models.Message
import com.example.springbootdemo.services.MessageService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MessageController(
    private val messageService: MessageService
) {
    @GetMapping
    fun index(): String {
        return "Spring Boot Framework"
    }

    @GetMapping("/messages")
    @ResponseStatus(HttpStatus.OK)
    fun getMessages(): List<Message> {
        return messageService.getMessages()
    }
}