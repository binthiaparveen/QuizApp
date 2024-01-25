package com.example.quizapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val Quistens = arrayOf("What is built-in database in Android studio?",
    "What is the full form of APK?", "In which year ,first android was released by Google?")
    private val options = arrayOf(arrayOf("MySql","Sql lite","Firebase","Fire"),
    arrayOf("Application programming interface","Android programming interface","Android package interface","Android programming information"),
    arrayOf("2010","2006","2008","2007"))
    private val correctAnswers = arrayOf(1, 0, 2)
   private var currentQuistionIndex = 0
   private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayquistens()
        binding.button1.setOnClickListener{
            checkAnswer(0)
        }
      binding.button2.setOnClickListener {
          checkAnswer(1)
      }
        binding.button3.setOnClickListener {
            checkAnswer(2)
        }
        binding.restart.setOnClickListener {
            restart()
        }
    }
    private fun correctButtonColors(buttonIndex:Int){
        when(buttonIndex){
            0-> binding.button1.setBackgroundColor(Color.GREEN)
            1-> binding.button2.setBackgroundColor(Color.GREEN)
            2-> binding.button3.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtoncolor(buttonIndex: Int){
        when(buttonIndex){
            0-> binding.button1.setBackgroundColor(Color.RED)
            1-> binding.button2.setBackgroundColor(Color.RED)
            2-> binding.button3.setBackgroundColor(Color.RED)
        }
    }
    private fun restartButtoncolors(){
        binding.button1.setBackgroundColor(Color.rgb(50,59,96))
        binding.button2.setBackgroundColor(Color.rgb(50,59,96))
        binding.button3.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        Toast.makeText(this@MainActivity,"Your Score:$score out of $(Quistens.size)",Toast.LENGTH_SHORT).show()
        binding.restart.isEnabled = true
    }
    private fun displayquistens(){
        binding.textView.text = Quistens[currentQuistionIndex]
        binding.button1.text = options[currentQuistionIndex][0]
        binding.button2.text = options[currentQuistionIndex][1]
        binding.button3.text = options[currentQuistionIndex][2]
        binding.button4.text = options[currentQuistionIndex][3]
        restartButtoncolors()
    }
    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuistionIndex]
        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        } else{
            wrongButtoncolor(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (
            currentQuistionIndex< Quistens.size - 1){
            currentQuistionIndex++
            binding.textView.postDelayed({displayquistens()},1000)
        } else{
            showResults()
        }

    }
    private fun restart(){
        currentQuistionIndex = 0
        score= 0
        displayquistens()
        binding.restart.isEnabled = false
    }
}