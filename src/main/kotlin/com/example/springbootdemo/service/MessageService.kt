package com.example.springbootdemo.service

import com.example.springbootdemo.dto.CreateMessageRequest
import com.example.springbootdemo.dto.MessageResponseDTO
import com.example.springbootdemo.dto.UpdateMessageRequest
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

    // fetch a message by ID
    fun messageById(id: String): Message? {
        return repository.findByIdOrNull(id)
    }

    // create a message
    fun createMessage(request: CreateMessageRequest): MessageResponseDTO {
        val newMessage = Message(
            text = request.text,
            author = request.author
        )

        return repository.save(newMessage).toDTO()
    }

    // update a message
    fun modifyMessage(id: String, request: UpdateMessageRequest): Message? {
        val message = messageById(id) ?: return null

        message.text = request.text

        return repository.save(message)
    }

    // delete a message
    fun removeMessage(id: String): Boolean {
        val exists = repository.existsById(id)
        if (exists) {
            repository.deleteById(id)
        }
        return exists
    }
}