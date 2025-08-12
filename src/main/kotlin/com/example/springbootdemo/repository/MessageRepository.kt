package com.example.springbootdemo.repository

import com.example.springbootdemo.entity.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository: JpaRepository<Message, String>