package com.example.springbootdemo.services

import com.example.springbootdemo.models.Message
import org.springframework.stereotype.Service

@Service
class MessageService {

    // get messages
    fun getMessages(): List<Message> {
        return listOf(
            Message("1", "Hello!"),
            Message("2", "Bonjour!"),
            Message("3", "Namaste!"),
            Message("4", "Hola!"),
            Message("5", "Privet!")
        )
    }
}