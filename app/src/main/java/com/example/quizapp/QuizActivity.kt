package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuestionProgress: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvQuestionText: TextView
    private lateinit var btnOption1: Button
    private lateinit var btnOption2: Button
    private lateinit var btnOption3: Button
    private lateinit var btnOption4: Button
    private lateinit var btnAction: Button

    private var questionList: ArrayList<Question> = ArrayList()
    private var currentPosition: Int = 1
    private var selectedOptionPosition: Int = -1 // 0-based index
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_question)

        // Bind Views
        tvQuestionProgress = findViewById(R.id.tvQuestionProgress)
        progressBar = findViewById(R.id.progressBar)
        tvQuestionText = findViewById(R.id.tvQuestionText)
        btnOption1 = findViewById(R.id.btnOption1)
        btnOption2 = findViewById(R.id.btnOption2)
        btnOption3 = findViewById(R.id.btnOption3)
        btnOption4 = findViewById(R.id.btnOption4)
        btnAction = findViewById(R.id.btnAction)

        // Load Questions
        questionList = QuestionBank.getQuestions()
        progressBar.max = questionList.size

        // Option click handlers: select and turn green
        btnOption1.setOnClickListener { selectOption(btnOption1, 0) }
        btnOption2.setOnClickListener { selectOption(btnOption2, 1) }
        btnOption3.setOnClickListener { selectOption(btnOption3, 2) }
        btnOption4.setOnClickListener { selectOption(btnOption4, 3) }

        // Action button click handler
        btnAction.setOnClickListener {
            handleActionClick()
        }

        setQuestion()
    }

    private fun setQuestion() {
        resetOptions()
        selectedOptionPosition = -1

        val currentQuestion = questionList[currentPosition - 1]

        // Update progress bar and counter
        progressBar.progress = currentPosition
        tvQuestionProgress.text = "Question $currentPosition/${questionList.size}"

        // Populate question and options
        tvQuestionText.text = currentQuestion.questionText
        btnOption1.text = currentQuestion.options[0]
        btnOption2.text = currentQuestion.options[1]
        btnOption3.text = currentQuestion.options[2]
        btnOption4.text = currentQuestion.options[3]

        // Dynamic button label: "SUBMIT" on the last question, "NEXT" on all previous
        if (currentPosition == questionList.size) {
            btnAction.text = "SUBMIT"
        } else {
            btnAction.text = "NEXT"
        }
    }

    private fun selectOption(button: Button, optionIndex: Int) {
        resetOptions()
        selectedOptionPosition = optionIndex

        // Turn selected option green
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.correct_green))
        button.setTextColor(Color.WHITE)
    }

    private fun resetOptions() {
        val options = listOf(btnOption1, btnOption2, btnOption3, btnOption4)
        for (btn in options) {
            btn.setBackgroundColor(ContextCompat.getColor(this, R.color.option_default_bg))
            btn.setTextColor(ContextCompat.getColor(this, R.color.text_dark))
        }
    }

    private fun handleActionClick() {
        // Validate that an option was chosen
        if (selectedOptionPosition == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        // Record score if correct
        val currentQuestion = questionList[currentPosition - 1]
        if (selectedOptionPosition == currentQuestion.correctOptionIndex) {
            score++
        }

        // If not the last question, move to the next question
        if (currentPosition < questionList.size) {
            currentPosition++
            setQuestion()
        } else {
            // Last question (SUBMIT clicked) -> Navigate to ResultActivity
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra(Constants.CORRECT_ANSWERS, score)
                putExtra(Constants.TOTAL_QUESTIONS, questionList.size)
            }
            startActivity(intent)
            finish()
        }
    }
}