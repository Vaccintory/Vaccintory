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
                    0 -> "ถามข้อมูลวัคซีน \nพิมพ์ 'vaccine' \n\nเกิดอุบัติเหตุ \nพิมพ์ 'accident' "
                    else -> "error"
                }
            }
            message.contains("vaccine") -> {
                when (0) {
                    0 -> "วัคซีนเด็กพื้นฐานสำหรับเด็ก ได้แก่ \n   วัคซีนวัณโรค(BCG) \n   วัคซีนตับอักเสบบี(HBV) \n   วัคซีนตับอักเสบบี(HBV) \n   วัคซีนคอตีบ-บาดทะยัก-ไอกรน (DPT)" +
                            "\n   วัคซีนโปลิโอ \n   วัคซีนหัด-หัดเยอรมัน-คางทูม(MMR) \n   วัคซีนไข้สมองอักเสบเจอี (JE) \n   วัคซีนป้องกันไข้หวัดใหญ่ \n   วัคซีนเอชพีวี(HPV)"+
                            "\n\nวัคซีนเสริมเพิ่มภูมิต้านทานโรคอื่นๆ ได้แก่ \n   วัคซีนโรต้า\n   วัคซีนนิวโมคอคคัส\n   วัคซีนฮิบ(Haemophilusinfluenzae type b)\n   วัคซีนป้องกันโรคอีสุกอีใส\n   วัคซีนตับอักเสบ เอ\n   วัคซีนไข้เลือดออก"
                    else -> "error"
                }
            }
            message.contains("accident") -> {
                when (0) {
                    0 -> "แผลลึก มีการปนเปื้อนดิน พิมพ์ A1"
                    else -> "error"
                }
            }
            message.contains("A1") -> {
                when (0) {
                    0 -> "หากมีแผลดังกล่าว(แผลลึก มีการปนเปื้อนดิน)\nควรพบแพทย์เพื่อปรึกษา\nเพื่อป้องกันไม่ให้เกิดบาดทะยัก"
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