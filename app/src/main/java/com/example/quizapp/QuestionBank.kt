package com.example.quizapp

object QuestionBank {

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        questionList.add(
            Question(
                id = 1,
                questionText = "What is the primary programming language recommended by Google for Android development?",
                options = listOf("Java", "Kotlin", "C++", "Flutter"),
                correctOptionIndex = 1
            )
        )

        questionList.add(
            Question(
                id = 2,
                questionText = "Which base class acts as an invisible container holding and arranging child Views?",
                options = listOf("ViewGroup", "Service", "BroadcastReceiver", "Intent"),
                correctOptionIndex = 0
            )
        )

        questionList.add(
            Question(
                id = 3,
                questionText = "Which Activity lifecycle callback is executed immediately when the screen becomes visible to the user?",
                options = listOf("onResume()", "onCreate()", "onStart()", "onRestart()"),
                correctOptionIndex = 2
            )
        )

        questionList.add(
            Question(
                id = 4,
                questionText = "Which inputType attribute masks entered characters for password fields?",
                options = listOf("textVisiblePassword", "textPassword", "numberSigned", "textCapWords"),
                correctOptionIndex = 1
            )
        )

        questionList.add(
            Question(
                id = 5,
                questionText = "Which command-line utility facilitates communication between your computer and emulator/device?",
                options = listOf("Gradle", "ADB (Android Debug Bridge)", "D8 Compiler", "ProGuard"),
                correctOptionIndex = 1
            )
        )

        return questionList
    }
}