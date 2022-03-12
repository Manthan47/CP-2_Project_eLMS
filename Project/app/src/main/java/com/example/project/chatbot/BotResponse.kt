package com.example.project.chatbot

import com.example.project.chatbot.Constants.COMMANDS
import com.example.project.chatbot.Constants.OPEN_GOOGLE
import com.example.project.chatbot.Constants.OPEN_SEARCH
import com.example.project.chatbot.Constants.OPEN_YOUTUBE
import com.example.project.chatbot.Constants.PLAY_YOUTUBE
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message = _message.toLowerCase()

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("solve") -> {
                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Kon'nichiwa!"
                    3 -> "Hola..."
                    else -> "error"
                }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }
            message.contains("mee too") -> {
                when (random) {
                    0 -> "Go do some cooking"
                    1 -> "Order food for delivery"
                    2 -> "You need to eat"
                    else -> "error"
                }
            }

            //What time is it?
            message.contains("time") && message.contains("?") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }
            //What date is it?
            message.contains("date") && message.contains("?") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            message.contains("!") && message.contains("commands") -> {
                COMMANDS
            }

            //Open Google
            message.contains("open") && message.contains("google") -> {
                OPEN_GOOGLE
            }

            //Open Youtube
            message.contains("open") && message.contains("youtube") -> {
                OPEN_YOUTUBE
            }

            //Play on Youtube
            message.contains("play") -> {
                PLAY_YOUTUBE
            }

            //Search on the internet
            message.contains("search") -> {
                OPEN_SEARCH
            }

            //When the bot doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}