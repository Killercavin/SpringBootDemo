package com.example.springbootdemo.controller

import com.example.springbootdemo.dto.CreateMessageRequest
import com.example.springbootdemo.exception.EmptyMessagesException
import com.example.springbootdemo.entity.Message
import com.example.springbootdemo.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/messages")
class MessageController(
    private val messageService: MessageService
) {
    @GetMapping
    fun messages(): String {
        return "Spring Boot Framework"
    }

    @GetMapping("/all")
    fun getAllMessages(): List<Message> {
        val messages = messageService.fetchMessages()

        return messages.ifEmpty {
            throw EmptyMessagesException("Empty messages")
        }
    }

    @PostMapping
    fun addMessage(@RequestBody request: CreateMessageRequest): Message {
        return messageService.createMessage(request)
    }
}