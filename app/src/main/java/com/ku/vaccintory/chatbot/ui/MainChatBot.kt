package com.ku.vaccintory.chatbot.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ku.vaccintory.R
import com.ku.vaccintory.chatbot.utils.BotResponse
import com.ku.vaccintory.chatbot.utils.Constants.RECEIVE_ID
import com.ku.vaccintory.chatbot.utils.Constants.SEND_ID
import com.ku.vaccintory.chatbot.data.Message
import kotlinx.android.synthetic.main.activity_main_chat_bot.*
import kotlinx.coroutines.*

import android.net.Uri
import kotlinx.android.synthetic.main.activity_main.*



class MainChatBot : AppCompatActivity() {

    private val TAG = "MainActivity"

    //You can ignore this messageList if you're coming from the tutorial,
    // it was used only for my personal debugging
    var messagesList = mutableListOf<Message>()

    private lateinit var adapter: MessagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_chat_bot)

        recyclerView()

        clickEvents()

        val random = (0..3).random()
        customBotMessage("สวัสดี!! ฉันคือบอทของ Vaccintory , ให้ช่วยอะไรไหม?")
    }

    private fun clickEvents() {

        //Send a message
        btn_send.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }

    }
    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
    private fun sendMessage() {
        val message = et_message.text.toString()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Message(message, SEND_ID))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.basicResponses(message)

                //Adds it to our local list
                messagesList.add(Message(response, RECEIVE_ID))

                //Inserts our message into the adapter
                adapter.insertMessage(Message(response, RECEIVE_ID))

                //Scrolls us to the position of the latest message
                rv_messages.scrollToPosition(adapter.itemCount - 1)


            }
        }
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                messagesList.add(Message(message, RECEIVE_ID))
                adapter.insertMessage(Message(message, RECEIVE_ID))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

}