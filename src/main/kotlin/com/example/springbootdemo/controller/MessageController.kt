package com.example.springbootdemo.controller

import com.example.springbootdemo.dto.CreateMessageRequest
import com.example.springbootdemo.dto.MessageResponseDTO
import com.example.springbootdemo.dto.UpdateMessageRequest
import com.example.springbootdemo.service.MessageService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class MessageController(
    private val messageService: MessageService
) {
    @GetMapping
    fun messages(): String {
        return "Spring Boot Framework"
    }

    // GET all messages
    @GetMapping("/messages/all")
    fun getAllMessages(): ResponseEntity<Map<String, Any>?> {
        val messages = messageService.fetchMessages()
        return ResponseEntity.ok(mapOf("data" to messages))
    }

    // GET a message by ID
    @GetMapping("/message/id")
    fun missingId(): ResponseEntity<Map<String, String>> {
        return ResponseEntity.badRequest()
            .body(mapOf("error" to "Message ID is required"))
    }

    @GetMapping("/message/id/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Map<String, Any>?>{
        val message = messageService.messageById(id)

        return if (message == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Message with id $id not found"))
        } else {
            ResponseEntity.ok(mapOf("data" to message))
        }
    }

    // POST a message
    @PostMapping("/message/create")
    fun addMessage(@Valid @RequestBody createMessageRequest: CreateMessageRequest):
            ResponseEntity<MessageResponseDTO?> {
        val createdMessage =  messageService.createMessage(createMessageRequest)

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage)
    }

    // PATCH a message
    @PatchMapping("/message/update/id")
    fun updateMessageMissingId(): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to "Message ID is required"))
    }

    @PatchMapping("/message/update/id/{id}")
    fun updateMessage(@PathVariable id: String, @Valid @RequestBody request: UpdateMessageRequest):
            ResponseEntity<Map<String, Any>> {
        val updatedMessage = messageService.modifyMessage(id, request)

        return if (updatedMessage == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Message with id $id not found"))
        } else {
            ResponseEntity.ok(mapOf("data" to updatedMessage))
        }
    }

    // DELETE a message
    @DeleteMapping("/message/delete/id")
    fun deleteMessageMissingId(): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to "Missing message ID"))
    }

    @DeleteMapping("/message/delete/id/{id}")
    fun deleteMessage(@PathVariable id: String): ResponseEntity<Map<String, Any>> {
        val deleted = messageService.removeMessage(id)
        return if (!deleted) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Message with id $id not found"))
        } else {
            ResponseEntity.noContent().build()
        }
    }

}