package kravets.k.v.reshi
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
class MainActivity : AppCompatActivity() {
    private lateinit var operand1: TextView
    private lateinit var operand2: TextView
    private lateinit var operator: TextView
    private lateinit var answerInput: EditText
    private lateinit var checkButton: Button
    private lateinit var startButton: Button
    private lateinit var statsTextView: TextView
    private var correctAnswers = 0
    private var totalAnswers = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        operand1 = findViewById(R.id.operand1)
        operand2 = findViewById(R.id.operand2)
        operator = findViewById(R.id.operator)
        answerInput = findViewById(R.id.answer_input)
        checkButton = findViewById(R.id.check_button)
        startButton = findViewById(R.id.start_button)
        statsTextView = findViewById(R.id.stats_textview)
        startButton.setOnClickListener {
            generateExample()
            startButton.isEnabled = false
            answerInput.isEnabled = true
            checkButton.isEnabled = true
        }
        checkButton.setOnClickListener {
            answerInput.isEnabled = false
            checkButton.isEnabled = false
            val userAnswer = answerInput.text.toString().toInt()
            val correctAnswer = calculateAnswer()
            if (userAnswer == correctAnswer) {
                statsTextView.setBackgroundColor(Color.GREEN)
                correctAnswers++
            } else {
                statsTextView.setBackgroundColor(Color.RED)
            }
            totalAnswers++
            updateStats()
        }
    }
    private fun generateExample() {
        val random = Random
        val num1 = random.nextInt(90) + 10
        val num2 = random.nextInt(90) + 10
        val operators = arrayOf('*', '/', '-', '+')
        val operation = operators.random()
        operand1.text = num1.toString()
        operand2.text = num2.toString()
        operator.text = operation.toString()
    }
    private fun calculateAnswer(): Int {
        val num1 = operand1.text.toString().toInt()
        val num2 = operand2.text.toString().toInt()
        val operation = operator.text.toString()
        return when (operation) {
            "*" -> num1 * num2
            "/" -> num1 / num2
            "-" -> num1 - num2
            "+" -> num1 + num2
            else -> -1
        }
    }
    private fun updateStats() {
        val accuracy = String.format("%.2f", (correctAnswers.toDouble() / totalAnswers) * 100)
        statsTextView.text = "Correct: $correctAnswers, Incorrect: ${totalAnswers - correctAnswers}, Accuracy: $accuracy%"
    }
}