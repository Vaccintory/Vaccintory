package com.ku.vaccintory.chatbot.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {


            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Hello"
                    2 -> "สวัสดี!!!"
                    else -> "error" }
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



            message.contains("help") -> {
                when (random) {
                    0 -> "ถามข้อมูลวัคซีน พิมพ์ 'vaccine' \nเกิดอุบัติเหตุ พิมพ์ 'accident' "
                    else -> "error"
                }
            }
            message.contains("vaccine") -> {
                when (random) {
                    0 -> "ถามข้อมูล  ------พิมพ์ '--' \n  "
                    else -> "error"
                }
            }



            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "ฉันไม่เข้าใจ?  สงสัยการใช้งาน พิมพ์ 'help'"
                    else -> "error"
                }
            }



        }
    }
}