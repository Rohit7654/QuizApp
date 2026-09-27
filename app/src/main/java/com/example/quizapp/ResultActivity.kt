package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_result)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvFeedback = findViewById<TextView>(R.id.tvFeedback)
        val btnRestart = findViewById<Button>(R.id.btnRestart)
        val btnExit = findViewById<Button>(R.id.btnExit)

        // Retrieve score details
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tvScore.text = "$correctAnswers / $totalQuestions"

        // Calculate score percentage
        val percentage = if (totalQuestions > 0) {
            (correctAnswers.toDouble() / totalQuestions * 100).toInt()
        } else {
            0
        }
        tvFeedback.text = "Accuracy: $percentage%"

        // Restart quiz
        btnRestart.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Close app
        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}