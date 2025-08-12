package com.example.springbootdemo.service

import com.example.springbootdemo.dto.CreateMessageRequest
import com.example.springbootdemo.dto.MessageResponseDTO
import com.example.springbootdemo.dto.toDTO
import com.example.springbootdemo.entity.Message
import com.example.springbootdemo.repository.MessageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MessageService(private val repository: MessageRepository) {

    // fetch all messages
    fun fetchMessages(): List<Message> {
        return repository.findAll()
    }

    // fetch message by id
    fun fetchMessageById(id: String): Message? {
        return repository.findByIdOrNull(id)
    }

    // add message
    fun createMessage(request: CreateMessageRequest): MessageResponseDTO {
        val newMessage = Message(
            author = request.author,
            text = request.text
        )

        val saved = repository.save(newMessage)
        return saved.toDTO()
    }

    // delete a message
    fun removeMessage(id: String) {
        repository.deleteById(id)
    }
}