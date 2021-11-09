package com.ku.vaccintory.chatbot.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message = _message.lowercase(Locale.getDefault())

        return when {


            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Hello"
                    else -> "error" }
            }
            message.contains("สวัสดี") -> {
                when (random) {
                    0 -> "ไง!"
                    1 -> "สวัสดี!!!"
                    2 -> "ว่าไง"
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
                when (0) {
                    0 -> "ถามข้อมูลวัคซีน พิมพ์ 'vaccine' \nเกิดอุบัติเหตุ พิมพ์ 'accident' "
                    else -> "error"
                }
            }
            message.contains("vaccine") -> {
                when (0) {
                    0 -> "ถามข้อมูล  ------พิมพ์ '--' \n  "
                    else -> "error"
                }
            }



            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "ฉันไม่เข้าใจ?  \nสงสัยการใช้งาน พิมพ์ 'help'"
                    1 -> "ฉันไม่เข้าใจ?  \nสงสัยการใช้งาน พิมพ์ 'Help'"
                    2 -> "ฉันไม่เข้าใจ?  \nสงสัยการใช้งาน พิมพ์ 'HELP'"
                    else -> "error"
                }
            }



        }
    }
}