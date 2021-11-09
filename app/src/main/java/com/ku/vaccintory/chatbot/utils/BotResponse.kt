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


            message.contains("help") -> {
                when (0) {
                    0 -> "ถามข้อมูลวัคซีน พิมพ์ 'vaccine' \nเกิดอุบัติเหตุ พิมพ์ 'accident' "
                    else -> "error"
                }
            }
            message.contains("vaccine") -> {
                when (0) {
                    0 -> "วัคซีนเด็กพื้นฐานสำหรับเด็ก ได้แก่ \n   วัคซีนวัณโรค(BCG) \n   วัคซีนตับอักเสบบี(HBV) \n   วัคซีนตับอักเสบบี(HBV) \n   วัคซีนคอตีบ-บาดทะยัก-ไอกรน (DPT)" +
                            "\n   วัคซีนโปลิโอ \n   วัคซีนหัด-หัดเยอรมัน-คางทูม(MMR) \n   วัคซีนไข้สมองอักเสบเจอี (JE) \n   วัคซีนป้องกันไข้หวัดใหญ่ \n   วัคซีนเอชพีวี(HPV)"
                    else -> "error"
                }
            }
            message.contains("accident") -> {
                when (0) {
                    0 -> "ขออภัย เรายังไม่มีข้อมูลเพิ่มเติมในส่วนนี้"
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