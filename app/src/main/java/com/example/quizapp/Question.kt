package com.example.quizapp

data class Question(
    val id: Int,
    val questionText: String,
    val options: List<String>,
    val correctOptionIndex: Int // 0-based index: 0 = Option 1, 1 = Option 2, etc.
)